
    // 마커를 담을 배열입니다
    var markers = [];

    // 다수의 마커를 컨트롤 하기 위해 넣음 클릭하면 색상 바뀌게
    var MARKER_WIDTH = 33, // 기본, 클릭 마커의 너비
    MARKER_HEIGHT = 36, // 기본, 클릭 마커의 높이
    OFFSET_X = 12, // 기본, 클릭 마커의 기준 X좌표
    OFFSET_Y = MARKER_HEIGHT, // 기본, 클릭 마커의 기준 Y좌표
    OVER_MARKER_WIDTH = 40, // 오버 마커의 너비
    OVER_MARKER_HEIGHT = 42, // 오버 마커의 높이
    OVER_OFFSET_X = 13, // 오버 마커의 기준 X좌표
    OVER_OFFSET_Y = OVER_MARKER_HEIGHT, // 오버 마커의 기준 Y좌표
    SPRITE_MARKER_URL = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markers_sprites2.png', // 스프라이트 마커 이미지 URL
    SPRITE_WIDTH = 126, // 스프라이트 이미지 너비
    SPRITE_HEIGHT = 146, // 스프라이트 이미지 높이
    SPRITE_GAP = 10; // 스프라이트 이미지에서 마커간 간격

    var markerSize = new kakao.maps.Size(MARKER_WIDTH, MARKER_HEIGHT), // 기본, 클릭 마커의 크기
    markerOffset = new kakao.maps.Point(OFFSET_X, OFFSET_Y), // 기본, 클릭 마커의 기준좌표
    overMarkerSize = new kakao.maps.Size(OVER_MARKER_WIDTH, OVER_MARKER_HEIGHT), // 오버 마커의 크기
    overMarkerOffset = new kakao.maps.Point(OVER_OFFSET_X, OVER_OFFSET_Y), // 오버 마커의 기준 좌표
    spriteImageSize = new kakao.maps.Size(SPRITE_WIDTH, SPRITE_HEIGHT); // 스프라이트 이미지의 크기

    //선택된 마커를 담을 변수
    selectedMarker = null;

    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
    center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};

    // 지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 장소 검색 객체를 생성합니다
    var ps = new kakao.maps.services.Places();

    // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({zIndex:1});

    // 키워드로 장소를 검색합니다
    searchPlaces();


    function searchPlaces() {

    var keyword = document.getElementById('keyword').value;

    if (!keyword.replace(/^\s+|\s+$/g, '')) {
    alert('키워드를 입력해주세요');
    return false;
}

    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch(keyword, placesSearchCB);
}

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

    // 정상적으로 검색이 완료됐으면
    // 검색 목록과 마커를 표출합니다
    displayPlaces(data);

    // 페이지 번호를 표출합니다
    displayPagination(pagination);

} else if (status === kakao.maps.services.Status.ZERO_RESULT) {
    alert('검색 결과가 존재하지 않습니다.');
    return;
} else if (status === kakao.maps.services.Status.ERROR) {
    alert('검색 결과 중 오류가 발생했습니다.');
    return;

}
}

    // 검색 결과 목록과 마커를 표출하는 함수입니다
    function displayPlaces(places) {

    var listEl = document.getElementById('placesList'),
    menuEl = document.getElementById('menu_wrap'),
    fragment = document.createDocumentFragment(),
    bounds = new kakao.maps.LatLngBounds(),
    listStr = '';

    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();

    for ( var i=0; i<places.length; i++ ) {
    // 마커를 생성하고 지도에 표시합니다
    var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
    marker = addMarker(placePosition, i),
    itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
    // LatLngBounds 객체에 좌표를 추가합니다
    bounds.extend(placePosition);

    // 마커와 검색결과 항목에 mouseover 했을때
    // 해당 장소에 인포윈도우에 장소명을 표시합니다
    // mouseout 했을 때는 인포윈도우를 닫습니다
    (function(marker, title) {
    kakao.maps.event.addListener(marker, 'mouseover', function() {
    displayInfowindow(marker, title);
});

    kakao.maps.event.addListener(marker, 'mouseout', function() {
    infowindow.close();
});

    itemEl.onmouseover =  function () {
    displayInfowindow(marker, title);
};

    itemEl.onmouseout =  function () {
    infowindow.close();
};
})(marker, places[i].place_name);

    fragment.appendChild(itemEl);
}

    // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
}

    // 검색결과 항목을 Element로 반환하는 함수입니다
    function getListItem(index, places) {

    var el = document.createElement('li'),
    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
    '<div class="info">' +
    '<h5>' + places.place_name + '</h5>';

    if (places.road_address_name) {
    itemStr += '<span>' + places.road_address_name + '</span>' +
    '<span class="jibun gray">' +  places.address_name  + '</span>';
} else {
    itemStr += '<span>' +  places.address_name  + '</span>';
}

    itemStr += '  <span class="tel">' + places.phone  + '</span>' +
    '</div>';

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
}

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    function addMarker(position, idx, title) {
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
    imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
    imgOptions =  {
    spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
    spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
    offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
},
    markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
    marker = new kakao.maps.Marker({
    position: position, // 마커의 위치
    // setDraggable:true,
    image: markerImage,
    clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
});

    var iwContent = '<div style="padding:30px;">이곳으로 정하시겠습니까?<button class="btn btn-outline-success" id="btn-xy">저장하기</button></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    iwRemoveable = true;  // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    var infowindow = new kakao.maps.InfoWindow({
    content : iwContent,
    removable : iwRemoveable
});

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {
    // 마커 위에 인포윈도우를 표시합니다
    infowindow.open(map, marker);
});



    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다
    marker.setDraggable(true); //마커 위치 변동할 수 있게 한다

    // 아래 코드는 위의 마커를 생성하는 코드에서 clickable: true 와 같이
    // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
    marker.setClickable(true);


    return marker;
}

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
    markers[i].setMap(null);
}
    markers = [];
}

    // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
    function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
    fragment = document.createDocumentFragment(),
    i;

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
    paginationEl.removeChild (paginationEl.lastChild);
}

    for (i=1; i<=pagination.last; i++) {
    var el = document.createElement('a');
    el.href = "#";
    el.innerHTML = i;

    if (i===pagination.current) {
    el.className = 'on';
} else {
    el.onclick = (function(i) {
    return function() {
    pagination.gotoPage(i);
}
})(i);
}

    fragment.appendChild(el);
}
    paginationEl.appendChild(fragment);
}

    // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
    // 인포윈도우에 장소명을 표시합니다
    function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

    infowindow.setContent(content);
    infowindow.open(map, marker);
}

    // 검색결과 목록의 자식 Element를 제거하는 함수입니다
    function removeAllChildNods(el) {
    while (el.hasChildNodes()) {
    el.removeChild (el.lastChild);
}
}

    function setMapType(maptype) {
    var roadmapControl = document.getElementById('btnRoadmap');
    var skyviewControl = document.getElementById('btnSkyview');
    if (maptype === 'roadmap') {
    map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);
    roadmapControl.className = 'selected_btn';
    skyviewControl.className = 'btn';
} else {
    map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
    skyviewControl.className = 'selected_btn';
    roadmapControl.className = 'btn';
}
}
    //줌인하는 버튼
    function zoomIn() {
    map.setLevel(map.getLevel() - 1);
}
    //축소하는 버튼
    function zoomOut() {
    map.setLevel(map.getLevel() + 1);
}

    //특정 마커의 위치
    function getInfo() {
    // 지도의 현재 중심좌표를 얻어옵니다
    var center = map.getCenter();

    // 지도의 현재 레벨을 얻어옵니다
    var level = map.getLevel();

    // 지도타입을 얻어옵니다
    var mapTypeId = map.getMapTypeId();

    // 지도의 현재 영역을 얻어옵니다
    var bounds = map.getBounds();

    // 영역의 남서쪽 좌표를 얻어옵니다
    var swLatLng = bounds.getSouthWest();

    // 영역의 북동쪽 좌표를 얻어옵니다
    var neLatLng = bounds.getNorthEast();

    // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
    var boundsStr = bounds.toString();


    var message = '지도 중심좌표는 위도 ' + center.getLat() + ', <br>';
    message += '경도 ' + center.getLng() + ' 이고 <br>';
    message += '지도 레벨은 ' + level + ' 입니다 <br> <br>';
    message += '지도 타입은 ' + mapTypeId + ' 이고 <br> ';
    message += '지도의 남서쪽 좌표는 ' + swLatLng.getLat() + ', ' + swLatLng.getLng() + ' 이고 <br>';
    message += '북동쪽 좌표는 ' + neLatLng.getLat() + ', ' + neLatLng.getLng() + ' 입니다';

    alert(message);

    // 개발자도구를 통해 직접 message 내용을 확인해 보세요.
    // ex) console.log(message);
    //     더블클릭했을 때 색상 바뀌게

    function addMarker(position, normalOrigin, overOrigin, clickOrigin) {

    // 기본 마커이미지, 오버 마커이미지, 클릭 마커이미지를 생성합니다
    var normalImage = createMarkerImage(markerSize, markerOffset, normalOrigin),
    overImage = createMarkerImage(overMarkerSize, overMarkerOffset, overOrigin),
    clickImage = createMarkerImage(markerSize, markerOffset, clickOrigin);

    // 마커를 생성하고 이미지는 기본 마커 이미지를 사용합니다
    var marker = new kakao.maps.Marker({
    map: map,
    position: position,
    image: normalImage
});

    // 마커 객체에 마커아이디와 마커의 기본 이미지를 추가합니다
    marker.normalImage = normalImage;

    // 마커에 mouseover 이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'mouseover', function() {

    // 클릭된 마커가 없고, mouseover된 마커가 클릭된 마커가 아니면
    // 마커의 이미지를 오버 이미지로 변경합니다
    if (!selectedMarker || selectedMarker !== marker) {
    marker.setImage(overImage);
}
});

    // 마커에 mouseout 이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'mouseout', function() {

    // 클릭된 마커가 없고, mouseout된 마커가 클릭된 마커가 아니면
    // 마커의 이미지를 기본 이미지로 변경합니다
    if (!selectedMarker || selectedMarker !== marker) {
    marker.setImage(normalImage);
}
});

    // 마커에 click 이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {

    // 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
    // 마커의 이미지를 클릭 이미지로 변경합니다
    if (!selectedMarker || selectedMarker !== marker) {

    // 클릭된 마커 객체가 null이 아니면
    // 클릭된 마커의 이미지를 기본 이미지로 변경하고
    !!selectedMarker && selectedMarker.setImage(selectedMarker.normalImage);

    // 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
    marker.setImage(clickImage);
}

    // 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
    selectedMarker = marker;
});
}
    // MakrerImage 객체를 생성하여 반환하는 함수입니다
    function createMarkerImage(markerSize, offset, spriteOrigin) {
    var markerImage = new kakao.maps.MarkerImage(
    SPRITE_MARKER_URL, // 스프라이트 마커 이미지 URL
    markerSize, // 마커의 크기
{
    offset: offset, // 마커 이미지에서의 기준 좌표
    spriteOrigin: spriteOrigin, // 스트라이프 이미지 중 사용할 영역의 좌상단 좌표
    spriteSize: spriteImageSize // 스프라이트 이미지의 크기
}
    );
    return markerImage;
}
}
