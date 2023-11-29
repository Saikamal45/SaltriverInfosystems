package com.Assessment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Coordinates {

	 public static double haversine(double lat1, double lon1, double lat2, double lon2) {
		// Radius of the Earth in meters
	        final double R = 6371000.0;

	     // Convert latitude and longitude differences from degrees to radians

	        double dLat = Math.toRadians(lat2 - lat1);
	        double dLon = Math.toRadians(lon2 - lon1);

	        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
	                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	                        Math.sin(dLon / 2) * Math.sin(dLon / 2);


	     // Calculate the central angle using the arctangent function
	        
	        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	        return R * c;
		 
	 }
	 
	// Generate points at regular intervals between two coordinates
	 
	 public static List<double[]> generatePoints(double[] coord1, double[] coord2, double interval){
		 
		// Calculate total distance between the two coordinates
		 
		 double distance = haversine(coord1[0], coord1[1], coord2[0], coord2[1]);
		 
		 // Calculate the number of points needed
		 
	        int numPoints = (int) (distance / interval) + 1;

	     // List to store the generated points
	        
	        List<double[]> points = new ArrayList<>();

	     // Iterate through each point along the route
	        
	        for (int i = 0; i < numPoints; i++) {
	        	// Calculate the fraction of distance covered at the current iteration
	        	
	            double fraction = (double) i / (numPoints - 1);
	            
	         // Use linear interpolation to calculate latitude at the current point
	            
	            double lat = coord1[0] + fraction * (coord2[0] - coord1[0]);
	            
	         // Use linear interpolation to calculate longitude at the current point
	            
	            double lon = coord1[1] + fraction * (coord2[1] - coord1[1]);
	            
	         // Add the calculated latitude and longitude as a new point to the list
	            
	            points.add(new double[]{lat, lon});
	        }

	        return points;
	 }
	 
	 public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);

	        // Get coordinates from the user
	        System.out.print("Enter latitude for coordinate 1: ");
	        double lat1 = scanner.nextDouble();
	        System.out.print("Enter longitude for coordinate 1: ");
	        double lon1 = scanner.nextDouble();

	        System.out.print("Enter latitude for coordinate 2: ");
	        double lat2 = scanner.nextDouble();
	        System.out.print("Enter longitude for coordinate 2: ");
	        double lon2 = scanner.nextDouble();

	     // It Will Convert user input to coordinate arrays

	        double[] coordinate1 = {lat1, lon1};
	        double[] coordinate2 = {lat2, lon2};

	        System.out.print("Enter interval in meters: ");
	        double interval = scanner.nextDouble();

	        List<double[]> points = generatePoints(coordinate1, coordinate2, interval);

	        // It will Print all the generated Coordinates
	        for (double[] point : points) {
	            System.out.println("Latitude: " + point[0] + ", Longitude: " + point[1]);
	        }

	      
	        scanner.close();
	}
	 
}
