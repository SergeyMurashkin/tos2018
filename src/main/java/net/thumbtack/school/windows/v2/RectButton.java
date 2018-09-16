package net.thumbtack.school.windows.v2;

public class RectButton {

  private String text;
  private Point topLeft;
  private Point bottomRight;
  private int xLeft;
  private int yTop;
  private int width;
  private int height;
  private boolean active = true;

  public RectButton(Point topLeft, Point bottomRight, boolean active, String text) {
    this.text=text;
    this.topLeft=topLeft;
    this.bottomRight=bottomRight;
    this.active=active;
  }

  public RectButton(int xLeft, int yTop, int width, int height, boolean active, String text) {
    this.text=text;
    this.xLeft=xLeft;
    this.yTop=yTop;
    this.width=width;
    this.height=height;
    this.active=active;
    Point tL = new Point(xLeft,yTop);
    this.setTopLeft(tL);
    Point bR = new Point(xLeft+width-1,yTop+height-1);
    this.setBottomRight(bR);
  }

  public RectButton(Point topLeft, Point bottomRight, String text) {
    this.text=text;
    this.topLeft=topLeft;
    this.bottomRight=bottomRight;
  }


  public RectButton(int xLeft, int yTop, int width, int height, String text) {
    this.text=text;
    this.yTop=yTop;
    this.xLeft=xLeft;
    this.width=width;
    this.height=height;
    Point tL = new Point(xLeft,yTop);
    this.setTopLeft(tL);
    Point bR = new Point(xLeft+width-1,yTop+height-1);
    this.setBottomRight(bR);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RectButton that = (RectButton) o;

    if (xLeft != that.xLeft) return false;
    if (yTop != that.yTop) return false;
    if (width != that.width) return false;
    if (height != that.height) return false;
    if (active != that.active) return false;
    if (text != null ? !text.equals(that.text) : that.text != null) return false;
    if (topLeft != null ? !topLeft.equals(that.topLeft) : that.topLeft != null) return false;
    return bottomRight != null ? bottomRight.equals(that.bottomRight) : that.bottomRight == null;
  }

  @Override
  public int hashCode() {
    int result = text != null ? text.hashCode() : 0;
    result = 31 * result + (topLeft != null ? topLeft.hashCode() : 0);
    result = 31 * result + (bottomRight != null ? bottomRight.hashCode() : 0);
    result = 31 * result + xLeft;
    result = 31 * result + yTop;
    result = 31 * result + width;
    result = 31 * result + height;
    result = 31 * result + (active ? 1 : 0);
    return result;
  }

  public int getxLeft() {
    return xLeft;
  }
  public void setxLeft(int xLeft) {
    this.xLeft = xLeft;
  }
  public int getyTop() {
    return yTop;
  }
  public void setyTop(int yTop) {
    this.yTop = yTop;
  }
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  public boolean isActive() { return active; }
  public void setActive(boolean active) { this.active = active; }
  public Point getTopLeft() { return topLeft; }
  public void setTopLeft(Point topLeft) { this.topLeft = topLeft; }
  public Point getBottomRight() { return bottomRight; }
  public void setBottomRight(Point bottomRight) { this.bottomRight = bottomRight; }

  public int getWidth() {
    width = getBottomRight().getX()- getTopLeft().getX()+1;
    return width; }

  public int getHeight() {
    height = getBottomRight().getY()-getTopLeft().getY()+1;
    return height; }

  public void moveTo(int x, int y) {
    int dx = x-getTopLeft().getX();
    int dy = y-getTopLeft().getY();
    Point bR = new Point(getBottomRight().getX()+dx,getBottomRight().getY()+dy);
    this.setBottomRight(bR);
    Point tL = new Point(x,y);
    this.setTopLeft(tL);
  }

  public void moveTo(Point point) {
    int dx = point.getX()-getTopLeft().getX();
    int dy = point.getY()-getTopLeft().getY();
    getBottomRight().setX(getBottomRight().getX()+dx);
    getBottomRight().setY(getBottomRight().getY()+dy);
    getTopLeft().setX(point.getX());
    getTopLeft().setY(point.getY());
  }

  public void moveRel(int dx, int dy) {
    topLeft.setX(topLeft.getX()+dx);
    topLeft.setY(topLeft.getY()+dy);
    bottomRight.setX(bottomRight.getX()+dx);
    bottomRight.setY(bottomRight.getY()+dy);
  }

  public void resize(double ratio) {
    width = (int) (ratio*(double)width);
    if (width==0) {
      width=1;
    }
    height = (int) (ratio*(double)height);
    if (height==0) {
      height=1;
    }
    Point bR = new Point(xLeft+width-1,yTop+height-1);
    this.setBottomRight(bR);
  }

  public boolean isInside(int x, int y) {
    boolean isInside = false;
    if (xLeft<=x && x<(xLeft+width) && yTop<=y && y<(yTop+height)) {
      isInside = true;
    }
    return isInside;
  }

  public boolean isInside(Point point) {
    boolean isInside = false;
    if (xLeft<=point.getX() && point.getX()<(xLeft+width) && yTop<=point.getY() && point.getY()<(yTop+height)) {
      isInside = true;
    }
    return isInside;
  }

  public boolean isIntersects(RectButton rectButton) {
    Point lT = new Point(rectButton.xLeft,rectButton.yTop);
    Point rT = new Point(rectButton.xLeft+rectButton.width-1,rectButton.yTop);
    Point lB = new Point(rectButton.xLeft,rectButton.yTop+rectButton.height-1);
    Point rB = new Point(rectButton.xLeft+rectButton.width-1,rectButton.yTop+rectButton.height-1);

    Point lT2 = new Point(xLeft,yTop);
    Point rT2 = new Point(xLeft+width-1,yTop);
    Point lB2 = new Point(xLeft,yTop+height-1);
    Point rB2 = new Point(xLeft+width-1,yTop+height-1);

    boolean isIntersects = false;
    if (isInside(lT)||isInside(rT)||isInside(lB)||isInside(rB)||
            rectButton.isInside(lT2)|| rectButton.isInside(rT2)|| rectButton.isInside(lB2)|| rectButton.isInside(rB2)) {
      isIntersects = true;
    }
    return isIntersects;
  }

  public boolean isInside(RectButton rectButton) {
    Point lT = new Point(rectButton.xLeft,rectButton.yTop);
    Point rT = new Point(rectButton.xLeft+rectButton.width-1,rectButton.yTop);
    Point lB = new Point(rectButton.xLeft,rectButton.yTop+rectButton.height-1);
    Point rB = new Point(rectButton.xLeft+rectButton.width-1,rectButton.yTop+rectButton.height-1);

    boolean isInside = false;
    if (isInside(lT)&&isInside(rT)&&isInside(lB)&&isInside(rB)) {
      isInside = true;
    }
    return isInside;
  }

  public boolean isFullyVisibleOnDesktop(Desktop desktop) {
    Point lT2 = new Point(xLeft,yTop);
    Point rT2 = new Point(xLeft+width-1,yTop);
    Point lB2 = new Point(xLeft,yTop+height-1);
    Point rB2 = new Point(xLeft+width-1,yTop+height-1);

    boolean isFullyVisibleOnDesktop = false;
    if ( lT2.isVisibleOnDesktop(desktop)&& rT2.isVisibleOnDesktop(desktop)&&
            lB2.isVisibleOnDesktop(desktop)&&rB2.isVisibleOnDesktop(desktop)) {
      isFullyVisibleOnDesktop = true;
    }
          return isFullyVisibleOnDesktop;

  }


}
