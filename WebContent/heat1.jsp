
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
	//double[] news_lat=g.getLatNews();
	//double[] news_long=g.getLongNews();
	double[] pol_lat = g.getLatPolitics();
	double[] pol_long = g.getLongPolitics();
	double[] sport_lat = g.getLatSports();
	double[] sport_long = g.getLongSports();
	double[] music_lat = g.getLatMusic();
	double[] music_long = g.getLongMusic();
	double[] tech_lat = g.getLatTechnology();
	double[] tech_long = g.getLongTechnology();

	//System.out.println(longii[34]);
	//String trends[] = {"trends","trend1", "trend2", "trend3", "trend4"};
	//double[] news_lat={0.0,10.0,45.0,-56.0};
	//double[] news_long={0.0,34.0,47.0,78.0};
%>
<body>

	<div id="floating-panel">
		<select id="trend" onchange="run()">

			<option value="1">Politics</option>
			<option value="2">Music</option>
			<option value="3">Sports</option>
			<option value="4">Technology</option>


		</select>
		
	</div>
	<div id="map"></div>

	
	<script>
		var map;
		

		function initMap() {
			map = new google.maps.Map(document.getElementById('map'), {
				zoom : 2,
				center : {
					lat : 0.0,
					lng : 0.0
				},
				mapTypeId : google.maps.MapTypeId.SATELLITE
			});}
		</script>
	<script type="text/javascript">
		function run() {
			var t = document.getElementById("trend");
			var value=t.options[t.selectedIndex].value;
			var mark_lat = new Array();
			var mark_longi = new Array();

			if (value==3) {
				initMap();
				mark_lat.length=0;
				mark_longi.length=0;
				 <%for (int j = 0; j < sport_lat.length; j++) {%>
					mark_lat.push(<%=sport_lat[j]%>);
					mark_longi.push(<%=sport_long[j]%>);
					<%}%> 
				//var mark_lat = [ 0.0, 10.0, 45.0, -56.0 ];
				//var mark_longi = [ 0.0, 34.0, 47.0, 78.0 ];
				for (var i = 0; i < mark_lat.length; i++) {
					if (i % 2 == 0) {
						var coord = {
							lat : mark_lat[i],
							lng : mark_longi[i]
						};
						var marker = new google.maps.Marker(
								{
									icon : 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
									position : coord,
									map : map
								});
					} else {
						var coord = {
							lat : mark_lat[i],
							lng : mark_longi[i]
						};
						var marker = new google.maps.Marker(
								{
									icon : 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
									position : coord,
									map : map
								});
					}

				}
			}
			
			else if (value==2) {
				initMap();
				mark_lat.length=0;
				mark_longi.length=0;
				 <%for (int j = 0; j < music_lat.length; j++) {%>
					mark_lat.push(<%=music_lat[j]%>);
					mark_longi.push(<%=music_long[j]%>);
					<%}%> 
				//var mark_lat = [ 0.0, 10.0, 45.0, -56.0 ];
				//var mark_longi = [ 0.0, 34.0, 47.0, 78.0 ];
				for (var i = 0; i < mark_lat.length; i++) {
					if (i % 2 == 0) {
						var coord = {
							lat : mark_lat[i],
							lng : mark_longi[i]
						};
						var marker = new google.maps.Marker(
								{
									icon : 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
									position : coord,
									map : map
								});
					} else {
						var coord = {
							lat : mark_lat[i],
							lng : mark_longi[i]
						};
						var marker = new google.maps.Marker(
								{
									icon : 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
									position : coord,
									map : map
								});
					}

				}
			}
			else if (value==1) {
				initMap();
				mark_lat.length=0;
				mark_longi.length=0;
				 <%for (int j = 0; j < pol_lat.length; j++) {%>
					mark_lat.push(<%=pol_lat[j]%>);
					mark_longi.push(<%=pol_long[j]%>);
					<%}%> 
				//var mark_lat = [ 0.0, 10.0, 45.0, -56.0 ];
				//var mark_longi = [ 0.0, 34.0, 47.0, 78.0 ];
				for (var i = 0; i < mark_lat.length; i++) {
					if (i % 2 == 0) {
						var coord = {
							lat : mark_lat[i],
							lng : mark_longi[i]
						};
						var marker = new google.maps.Marker(
								{
									icon : 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
									position : coord,
									map : map
								});
					} else {
						var coord = {
							lat : mark_lat[i],
							lng : mark_longi[i]
						};
						var marker = new google.maps.Marker(
								{
									icon : 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
									position : coord,
									map : map
								});
					}

				}
			}
			else if (value==4) {
				initMap();
				mark_lat.length=0;
				mark_longi.length=0;
				 <%for (int j = 0; j < tech_lat.length; j++) {%>
					mark_lat.push(<%=tech_lat[j]%>);
					mark_longi.push(<%=tech_long[j]%>);
					<%}%> 
				//var mark_lat = [ 0.0, 10.0, 45.0, -56.0 ];
				//var mark_longi = [ 0.0, 34.0, 47.0, 78.0 ];
				for (var i = 0; i < mark_lat.length; i++) {
					if (i % 2 == 0) {
						var coord = {
							lat : mark_lat[i],
							lng : mark_longi[i]
						};
						var marker = new google.maps.Marker(
								{
									icon : 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
									position : coord,
									map : map
								});
					} else {
						var coord = {
							lat : mark_lat[i],
							lng : mark_longi[i]
						};
						var marker = new google.maps.Marker(
								{
									icon : 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
									position : coord,
									map : map
								});
					}

				}
			}
		}
	</script>
	

	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=xxxxxxx&signed_in=true&libraries=visualization&callback=initMap">
		
	</script>

</body>
</html>