
public class BruteCollinearPoints {
    private int numSegments = 0;
    private LineSegment[] lineSegments;
    
    public BruteCollinearPoints(Point[] points) {
        double firstSlope = 0.0;
        double slope = 0.0;
        
        firstSlope = points[0].slopeTo(points[1]);
        
        for (int i = 2; i < points.length; i++) {
            slope = points[0].slopeTo(points[i]);
        }
        
    }
    
    public int numberOfSegments() {
        return numSegments;
    }
    
    public LineSegment[] segments() {
        return lineSegments;
    }
}
