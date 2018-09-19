package net.thumbtack.school.windows.v1;

public class RoundButton {

    private Point center;
    private int radius;
    private boolean active = true;

    public RoundButton(Point center, int radius, boolean active) {
	this.center = center;
	this.radius = radius;
	this.active = active;
    }

    public RoundButton(int xCenter, int yCenter, int radius, boolean active) {
	this.radius = radius;
	this.active = active;
	center = new Point(xCenter, yCenter);
    }

    public RoundButton(Point center, int radius) {
	this.center = center;
	this.radius = radius;
    }

    public RoundButton(int xCenter, int yCenter, int radius) {
	this.radius = radius;
	center = new Point(xCenter, yCenter);
    }

    public Point getCenter() {
	return center;
    }

    public int getRadius() {
	return radius;
    }

    public boolean isActive() {
	return active;
    }

    public void setCenter(int x, int y) {
	center = new Point(x, y);
    }

    public void setRadius(int radius) {
	this.radius = radius;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

    public void moveTo(int x, int y) {
	setCenter(x, y);
    }

    public void moveTo(Point point) {
	setCenter(point.getX(), point.getY());
    }

    public void moveRel(int dx, int dy) {
	setCenter(center.getX() + dx, center.getY() + dy);
    }

    public void resize(double ratio) {
	radius = (int) ((double) radius * ratio);
	if (radius < 1) {
	    radius = 1;
	}
    }

    public boolean isInside(int x, int y) {
	double dx = (double) center.getX() - x;
	double dy = (double) center.getY() - y;
	int l = (int) Math.sqrt(dx * dx + dy * dy);
	return radius >= l;
    }

    public boolean isInside(Point point) {
	double dx = (double) center.getX() - point.getX();
	double dy = (double) center.getY() - point.getY();
	int l = (int) Math.sqrt(dx * dx + dy * dy);
	return radius >= l;
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
	return radius <= center.getX() && center.getX() < desktop.getWidth() - radius
		&& radius <= center.getY() && center.getY() < desktop.getHeight() - radius;

    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;

	RoundButton that = (RoundButton) o;

	if (radius != that.radius) return false;
	if (active != that.active) return false;
	return center != null ? center.equals(that.center) : that.center == null;
    }

    @Override
    public int hashCode() {
	int result = center != null ? center.hashCode() : 0;
	result = 31 * result + radius;
	result = 31 * result + (active ? 1 : 0);
	return result;
    }
}
