import sys,os
import datetime
import random
import exrex
import json

# To output color text in terminal
class color:
   PURPLE = '\033[95m'
   CYAN = '\033[96m'
   DARKCYAN = '\033[36m'
   BLUE = '\033[94m'
   GREEN = '\033[92m'
   YELLOW = '\033[93m'
   RED = '\033[91m'
   BOLD = '\033[1m'
   UNDERLINE = '\033[4m'
   END = '\033[0m'

if ((len(sys.argv) < 7)):
    print """\
This script creates file as per GIS format( accepted by DMS App )""" + color.BOLD + color.CYAN + """

Usage:  python autoGISDMS.py latitude longitude noOfFiles noOfDevices time_range dirName
""" + color.END + """
1. Filename will be randomly spread in the area near latitude and longitude
2. Give SOURCE the same as given in DMS app
3. time_range : Time difference from the current time
4. dirName : Directory at which you want to rename the files

VARIABLES
----------
1. Type Of GIS Shape (Point, LineString, Polygon)


EXTENSION OF FILE CREATED
--------------------------
1. .geojson
"""
    sys.exit(1)


lat = float(sys.argv[1])
lon = float(sys.argv[2])
noOfFiles = int(sys.argv[3])
noOfDevices = int(sys.argv[4])
TTL = 50
time_range = int(sys.argv[5])
dirName = sys.argv[6]

type_list = ["Victim","Shelter","Food","Health"]
shape_list = ["Point", "LineString", "Polygon"]
source_list = []

# Spread messages in are of lat-long 
def getLatLong():
	val = random.randint(0,100)
	if val < 20:
		ddd = 2000

	else:
		ddd = 1500

	v = random.randint(0,3)
	if v == 0:
		la = float(lat - float(random.randint(0,ddd))/1000000.0)
		lo = float(lon + float(random.randint(0,ddd))/1000000.0)
	elif v==1:
		la = float(lat + float(random.randint(0,ddd))/1000000.0)
		lo = float(lon - float(random.randint(0,ddd))/1000000.0)
	elif v==2:
		la = float(lat + float(random.randint(0,ddd))/1000000.0)
		lo = float(lon + float(random.randint(0,ddd))/1000000.0)
	elif v==3:
		la = float(lat - float(random.randint(0,ddd))/1000000.0)
		lo = float(lon - float(random.randint(0,ddd))/1000000.0)
	
	la = "%2.6f"% (la,)
	lo = "%2.6f"% (lo,)
	#print str(la) +  ',' + str(lo)
	return (la,lo)


def random_date(start, end):
    """
    This function will return a random datetime between two datetime 
    objects.
    """
    delta = end - start
    int_delta = (delta.days * 24 * 60 * 60) + delta.seconds
    random_second = random.randrange(abs(int_delta))
    return start + datetime.timedelta(seconds=random_second)

def getTime():
	d2 = datetime.datetime.now()
	change = random.choice([True, False])
	if change:
		d1 = d2 + datetime.timedelta(hours=time_range)
	else:	
		d1 = d2 - datetime.timedelta(hours=time_range)	
	return random_date(d1, d2).strftime('%Y%m%d%H%M%S')

def computeSourceList():
	count = 0
	while count < noOfDevices:
		# Generate Indian Phone Numbers
		source_list.append(exrex.getone('[7-9]{2}\d{8}').encode('ascii', 'ignore'))
		count = count + 1
def generatePoint():
	print "-- Generating Point --"
def generateLineString():
	print "-- Generating LineString --"
def generatePolygon():
	print "-- Generating Polygon --"

computeSourceList()
for i in range(noOfFiles):
	la,lo = getLatLong()

	time_r = getTime()
#	print "IMG_" + str(TTL) + "_" + random.choice(type_list) + "_" + random.choice(source_list) + "_defaultMcs_" + str(la) + "_" + str(lo) + "_" + time_r + "_1.jpg"	
	# Create 
	geoMessage = {}
	geoMessage["type"] = "FeatureCollection"
	geoMessage["features"] = []
	geoMessage["features"].append("")
	geoMessage["features"][0] = {}
	
	geoMessage["features"][0]["properties"] = {}
	geoMessage["features"][0]["properties"]["PO"] = {}
	geoMessage["features"][0]["properties"]["PR"] = {}
	geoMessage["features"][0]["type"] = "Feature"
	geoMessage["features"][0]["properties"]["Source"] = random.choice(source_list) 
	geoMessage["features"][0]["properties"]["PO"]["timestamp"] = getTime()
	geoMessage["features"][0]["properties"]["PR"]["timestamp"] = getTime()
	geoMessage["features"][0]["properties"]["PO"]["coordinate"] = getLatLong()
	geoMessage["features"][0]["properties"]["PR"]["coordinate"] = getLatLong()
	geoMessage["features"][0]["geometry"] = {}

	# Generate Random Shapes (1 - Point, 2 - LineString, 3 - Polygon)
	shape_rand = random.choice(shape_list)

	if shape_rand == "Point":
		generatePoint()
	elif shape_rand == "LineString":
		generateLineString()
	else:
		generatePolygon()

	geoMessage["features"][0]["geometry"]["type"] = shape_rand
	print json.dumps(geoMessage)