public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        int numbers = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    public static Body[] readBodies(String filename) {
        In in = new In(filename);
        int numbers = in.readInt();
        Body[] Bodies = new Body[numbers];
        double radius = in.readDouble();
        for (int i = 0; i < numbers; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            Bodies[i] = new Body(xP, yP, xV, yV, m, img);
        }
        return Bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Body[] bodies = NBody.readBodies(filename);



        for (int i = 0; i < bodies.length; i++) {
            bodies[i].draw();
        }


        for (int t = 0; t < T; t += dt) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-radius,radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            StdDraw.show();

            for (int i = 0; i < bodies.length; i++) {
                bodies[i].draw();
            }

            StdDraw.pause(100);
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
}
    }
}