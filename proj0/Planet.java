public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    static final double G = 6.67e-11;


    public Planet(double xP, double yP, double xV,double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    public double calcDistance(Planet b) {
        double dx = xxPos-b.xxPos;
        double dy = yyPos-b.yyPos;
        double r = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
        return r;
    }

    public double calcForceExertedBy(Planet b) {
        double F = (G*mass*b.mass)/(Math.pow(calcDistance(b), 2));
        return F;
    }

    public double calcNetForceExertedByX(Planet[] allBodys) {

        double x = 0;
        for (int i = 0; i < allBodys.length; i++) {
            if (equals(allBodys[i])) {
                continue;
            } else {
                double dx = allBodys[i].xxPos-xxPos;
                x = x + calcForceExertedBy(allBodys[i])*(dx/calcDistance(allBodys[i]));
            }
        }
        return x;
    }

    public double calcNetForceExertedByY(Planet[] allBodys) {

        double y = 0;
        for (int i = 0; i < allBodys.length; i++) {
            if (equals(allBodys[i])) {
                continue;
            } else {
                double dy = allBodys[i].yyPos-yyPos;
                y = y + calcForceExertedBy(allBodys[i])*(dy/calcDistance(allBodys[i]));
            }
        }
        return y;
    }

    public void update(double dt,double fX,double fY) {
        double ax = fX/mass;
        double ay = fY/mass;
        xxVel = xxVel + ax*dt;
        yyVel = yyVel + ay*dt;
        xxPos = xxPos + xxVel*dt;
        yyPos = yyPos + yyVel*dt;
    }

    public void draw() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-NBody.readRadius("./data/planets.txt"),NBody.readRadius("./data/planets.txt"));
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
        StdDraw.show();
    }
}