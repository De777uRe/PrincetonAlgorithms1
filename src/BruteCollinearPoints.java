
public class BruteCollinearPoints {
    private int numSegments = 0;
    private LineSegment[] lineSegments;
    
    public BruteCollinearPoints(Point[] points) {
    	if (points == null) {
    		throw new IllegalArgumentException("Null value passed to constructor");
    	}
    	
    	for (int i = 0; i < points.length; i++) {
    		for (int j = 0; j < points.length; j++) {
    			if (points[i] == null) {
    				throw new IllegalArgumentException("Null point found in array");
    			}
    			else {
    				if (!points[i].equals(points[j])) {
        				if (points[i].compareTo(points[j]) == 0) {
        					throw new IllegalArgumentException("Array of points contains repeated point");
        				}
        			}
    			}
    			
    		}
    	}
    	
    	numSegments = points.length - 1;
    	
        double firstSlope = 0.0;
        double secondSlope = 0.0;
        double thirdSlope = 0.0;
        
        firstSlope = points[0].slopeTo(points[1]);
        secondSlope = points[0].slopeTo(points[2]);
        thirdSlope = points[0].slopeTo(points[3]);
        
        if (firstSlope == secondSlope && firstSlope == thirdSlope) {
        	Point minPt = points[0];
        	Point maxPt = points[0];

        	for (int i = 1; i < points.length; i++) {
        		if (points[i].compareTo(minPt) < 0) {
        			minPt = points[i];
        		}
        		else if (points[i].compareTo(maxPt) > 0) {
        			maxPt = points[i];
        		}
        	}
        	
        	LineSegment ptsLine = new LineSegment(minPt, maxPt);
        	lineSegments[0] = ptsLine;
        }
    }
    
    public int numberOfSegments() {
        return numSegments;
    }
    
    public LineSegment[] segments() {
        return lineSegments;
    }
}
