package com.example.pamarket00.common;

import com.example.pamarket00.dto.FileDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//@Component
//public class FileUtils {
//  public List<FileDto> parseFileInfo(int fileBoardNum, MultipartHttpServletRequest uploadFiles) throws Exception {
////    업로드된 파일 정보 확인
//    if (ObjectUtils.isEmpty(uploadFiles)) {
//      return null; // 파일 정보가 없으면 종료
//    }
//
//    List<FileDto> fileList = new ArrayList<>();
//
////    파일 저장 폴더 생성
////    날짜를 년월일 형태로 생성
//    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
////    현재 서버에 설정된 지역 정보를 기반으로 현재 시간을 가져옴
//    ZonedDateTime current = ZonedDateTime.now();
////    전체 경로 생성, images 폴더 아래에 현재 날짜로 된 폴더를 경로 지정
//    String path1 = "images/" + current.format(format);
//    String path2 = "src/main/resources/static/yuriImage/"+current.format(format);
//
////    지정한 경로로 파일 클래스 타입의 객체 생성
//    File file = new File(path2);
////    지정한 경로가 존재하는지 여부 확인 (있으면 true, 없으면 false)
//    if (file.exists() == false) {
//      file.mkdirs(); // 폴더 생성
//    }
//
////    업로드된 파일 정보에서 모든 파일 이름을 가져옴
//    Iterator<String> iterator = uploadFiles.getFileNames();
//
//    String newFileName; // 새로운 파일 이름을 저장하기 위한 변수
//    String originalFileExtension; // 확장자를 저장하기 위한 변수
//    String contentType; // 파일 타입을 저장하기 위한 변수
//
////    업로드된 모든 파일 정보를 가져옴
//    while (iterator.hasNext()) {
////      가져온 이름을 기반으로 파일 정보를 가져옴
//      List<MultipartFile> list = uploadFiles.getFiles(iterator.next());
//
//      for (MultipartFile multipartFile : list) {
//        if (multipartFile.isEmpty() == false) {
////          가져온 파일 정보에서 확장자 정보를 가져옴
//          contentType = multipartFile.getContentType();
//
//          if (ObjectUtils.isEmpty(contentType)) {
//            break;
//          }
////          확인된 확장자 타입에 따라서 확장자명 추가
//          else {
//            if (contentType.contains("image/jpeg")) {
//              originalFileExtension = ".jpg";
//            }
//            else if (contentType.contains("image/png")) {
//              originalFileExtension = ".png";
//            }
//            else if (contentType.contains("image/gif")) {
//              originalFileExtension = ".gif";
//            }
//            else {
//              break;
//            }
//          }
//
////          nanoTime() : 자바 언어에서 현재 시간을 기준으로 1/1000초까지 계산된 시간 정보를 가져옴
////          서버에 파일을 저장 시 동일한 이름의 파일을 저장할 수 없으므로, 현재 시간을 기준으로 파일 이름을 변경하여 저장하는 방식을 사용함
////          동일한 시간에 서버에서 접속하여 파일을 업로드하는 경우가 발생할 수 있으므로 최대한 파일 이름의 중복을 피하기위해서 nanoTime()을 사용하여 이름의 중복을 피함
//          newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
//
////          파일 정보를 저장하기 위한 BoardFileDto 클래스 타입의 객체 생성
//          FileDto boardFile = new FileDto();
//          boardFile.setFileBoardNum(fileBoardNum); // 현재 게시물 번호 저장
////          boardFile.setF(multipartFile.getSize()); // 파일 크기 저장
//          boardFile.setFileName(multipartFile.getOriginalFilename()); // 실제 파일명 저장
//          boardFile.setFilePath(path1 + "/" + newFileName); // 위에서 생성한 폴더 경로와 새로 생성된 중복방지를 위한 이름을 합해서 저장
//
////          실제 데이터베이스에 저장하기 위한 파일 정보를 가지고 있는 객체 리스트에 저장
//          fileList.add(boardFile);
//
////          파일 객체 생성
//          file = new File(path2 + "/" + newFileName);
////          실제 파일 정보를 바탕으로 서버에 실제로 파일을 저장
//          multipartFile.transferTo(file);
//        }
//      }
//    }
////    위에서 생성한 파일 정보 리스트를 반환
//  return fileList;
//  }
//}


//정종율씨
@Component
public class FileUtils {
  public List<FileDto> parseFileInfo(int fileBoardNum, MultipartHttpServletRequest uploadFiles) throws Exception {
    if(ObjectUtils.isEmpty(uploadFiles)) return null;

    List<FileDto> fileList = new ArrayList<>();

    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
    ZonedDateTime current = ZonedDateTime.now();

    String path1 = "src/main/resources/static/img/" + current.format(format);
    String path2 = "/img/" + current.format(format);

    File file = new File(path1);
    if(!file.exists()) file.mkdirs();


    Iterator<String> iterator = uploadFiles.getFileNames();

    String newFileName;
    String originalFileExtension;
    String contentType;

    while (iterator.hasNext()) {
      List<MultipartFile> list = uploadFiles.getFiles(iterator.next());

      for (MultipartFile multipartFile : list) {
        if (!multipartFile.isEmpty()) {
          contentType = multipartFile.getContentType();

          if (ObjectUtils.isEmpty(contentType)) break;
          else {
            if(contentType.contains("image/jpeg")) originalFileExtension = ".jpg";
            else if(contentType.contains("image/png")) originalFileExtension = ".png";
            else if(contentType.contains("image/gif")) originalFileExtension = ".gif";
            else break;
          }

          newFileName = Long.toString(System.nanoTime()) + originalFileExtension;

          FileDto fileDto = new FileDto();
          fileDto.setFileBoardNum(fileBoardNum);
//          fileDto.setFileSize(multipartFile.getSize());
          fileDto.setFileName(multipartFile.getOriginalFilename());
          fileDto.setFilePath(path2 + "/" + newFileName);

          fileList.add(fileDto);

          file = new File(path1 + "/" + newFileName);
          multipartFile.transferTo(file);
        }
      }
    }

    return fileList;
  }
}












