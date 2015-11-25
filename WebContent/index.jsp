
<%@ page import="com.twitter.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Heatmaps</title>
<style>
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}

#map {
	height: 100%;
	weight: 80%
}

#floating-panel {
	position: absolute;
	top: 10px;
	left: 25%;
	z-index: 5;
	background-color: #fff;
	padding: 5px;
	border: 1px solid #999;
	text-align: center;
	font-family: 'Roboto', 'sans-serif';
	line-height: 30px;
	padding-left: 10px;
}

#floating-panel {
	background-color: #fff;
	border: 1px solid #999;
	left: 25%;
	padding: 5px;
	position: absolute;
	top: 10px;
	z-index: 5;
}
</style>
</head>
<%
	GetTweets g = new GetTweets();
	String key = g.loadMenu();
	Thread.sleep(500);
	//MongoDBDao mdb= new MongoDBDaoImpl();
	double lati[] = g.getLatitude(key);
	//System.out.println(lati[34]);
	double longii[] = g.getLongitude(key);
	//System.out.println(longii[34]);
	String trends[] = {"trend1", "trend2", "trend3", "trend4"};
	double[] news_lat={0.0,10.0,45.0,-56.0};
	double[] news_long={0.0,34.0,47.0,78.0};
	
%>
<body>
<div id="floating-panel">
		<button onclick="pageChange()">Show Categories</button>
		<button onclick="toggleHeatmap()">Toggle Heatmap</button>
		<button onclick="changeGradient()">Change gradient</button>
		<button onclick="changeRadius()">Change radius</button>
		<button onclick="changeOpacity()">Change opacity</button>
	</div>
	<div id="map"></div>

	<script type="text/javascript">
  setTimeout(function () { location.reload(true); }, 15000);
	</script>
	<script>
		var map, heatmap;		
		var mapCoordinates = new Array();
		//var latitude=${latitude};
		//var longitude=${longitude};
		//window.location.reload();
		
		function initMap() {
			map = new google.maps.Map(document.getElementById('map'), {
				zoom : 2,
				center : {
					lat : 0.0,
					lng : 0.0
				},
				mapTypeId : google.maps.MapTypeId.SATELLITE
			});
	<%for (int j = 0; j < lati.length; j++) {%>
		mapCoordinates.push(new google.maps.LatLng(<%=lati[j]%>,<%=longii[j]%>
		));
	<%}%>
		heatmap = new google.maps.visualization.HeatmapLayer({
				data : mapCoordinates,
				map : map
			});
		}


		function toggleHeatmap() {
			heatmap.setMap(heatmap.getMap() ? null : map);
		}

		function changeGradient() {
			var gradient = [ 'rgba(0, 255, 255, 0)', 'rgba(0, 255, 255, 1)',
					'rgba(0, 191, 255, 1)', 'rgba(0, 127, 255, 1)',
					'rgba(0, 63, 255, 1)', 'rgba(0, 0, 255, 1)',
					'rgba(0, 0, 223, 1)', 'rgba(0, 0, 191, 1)',
					'rgba(0, 0, 159, 1)', 'rgba(0, 0, 127, 1)',
					'rgba(63, 0, 91, 1)', 'rgba(127, 0, 63, 1)',
					'rgba(191, 0, 31, 1)', 'rgba(255, 0, 0, 1)' ]
			heatmap.set('gradient', heatmap.get('gradient') ? null : gradient);
		}

		function changeRadius() {
			heatmap.set('radius', heatmap.get('radius') ? null : 20);
		}

		function changeOpacity() {
			heatmap.set('opacity', heatmap.get('opacity') ? null : 0.2);

		}
		
		</script>

	<script type="text/javascript">
 function pageChange()
 {
var pageURL= window.location.href;
var res = pageURL.replace("index", "heat1");
window.location.href=res;
 }
</script>	
	
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=xxxxxxxx&signed_in=true&libraries=visualization&callback=initMap">
		
	</script>
	
</body>
</html>