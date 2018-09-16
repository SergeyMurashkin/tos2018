package net.thumbtack.school.windows.v2;

public class RoundButton {

  private Point center;
  private int xCenter;
  private int yCenter;
  private int radius;
  private boolean active = true;
  private String text;

  public RoundButton(Point center, int radius, boolean active, String text) {
    this.text=text;
    this.center=center;
    this.radius=radius;
    this.active=active;
    xCenter = center.getX();
    yCenter = center.getY();
  }

  public RoundButton(int xCenter, int yCenter, int radius, boolean active, String text) {
    this.text=text;
    this.xCenter = xCenter;
    this.yCenter = yCenter;
    this.radius = radius;
    this.active = active;
    center = new Point(xCenter,yCenter);
  }

  public RoundButton(Point center, int radius, String text) {
    this.text=text;
    this.center = center;
    this.radius = radius;
    xCenter = center.getX();
    yCenter = center.getY();
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RoundButton that = (RoundButton) o;

    if (xCenter != that.xCenter) return false;
    if (yCenter != that.yCenter) return false;
    if (radius != that.radius) return false;
    if (active != that.active) return false;
    if (center != null ? !center.equals(that.center) : that.center != null) return false;
    return text != null ? text.equals(that.text) : that.text == null;
  }

  @Override
  public int hashCode() {
    int result = center != null ? center.hashCode() : 0;
    result = 31 * result + xCenter;
    result = 31 * result + yCenter;
    result = 31 * result + radius;
    result = 31 * result + (active ? 1 : 0);
    result = 31 * result + (text != null ? text.hashCode() : 0);
    return result;
  }

  public RoundButton(int xCenter, int yCenter, int radius, String text){
    this.text=text;
    this.xCenter = xCenter;
    this.yCenter = yCenter;
    this.radius = radius;
    center = new Point(xCenter,yCenter);
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
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

  public void setCenter(int x,int y) {
    center = new Point(x,y);
    xCenter=x;
    yCenter=y;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void moveTo(int x, int y) {
    setCenter(x,y);
  }

  public void moveTo(Point point) {
    setCenter(point.getX(),point.getY());
  }

  public void moveRel(int dx, int dy) {
    setCenter(xCenter+dx,yCenter+dy);
  }

  public void resize(double ratio) {
    radius = (int)((double)radius*ratio);
    if (radius<1) {
      radius=1;
    }
  }

  public boolean isInside(int x, int y) {
    boolean isInside = false;
    double dx = (double) xCenter-x;
    double dy = (double) yCenter-y;
    int l = (int) Math.sqrt(dx*dx+dy*dy);
    if (radius>=l) {
      isInside=true;
    }
    return isInside;
  }

  public boolean isInside(Point point) {
    boolean isInside = false;
    double dx = (double) xCenter-point.getX();
    double dy = (double) yCenter-point.getY();
    int l = (int) Math.sqrt(dx*dx+dy*dy);
    if (radius>=l) {
      isInside=true;
    }
    return isInside;
  }

  public boolean isFullyVisibleOnDesktop(Desktop desktop) {

    boolean isFullyVisibleOnDesktop = false;
    if (radius<=xCenter && xCenter<desktop.getWidth()-radius && radius<=yCenter && yCenter<desktop.getHeight()-radius) {
      isFullyVisibleOnDesktop = true;
    }
    return isFullyVisibleOnDesktop;

  }

}
