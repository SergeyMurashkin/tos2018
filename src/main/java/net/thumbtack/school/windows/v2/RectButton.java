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
    setTopLeft(topLeft);
    setBottomRight(bottomRight);
    this.active = active;
    setText(text);
  }

  public RectButton(int xLeft, int yTop, int width, int height, boolean active, String text) {
    setTopLeft(new Point(xLeft,yTop));
    setBottomRight(new Point(xLeft+width-1,yTop+height-1));
    this.active = active;
    this.text=text;
  }

  public RectButton(Point topLeft, Point bottomRight, String text) {
    setTopLeft(topLeft);
    setBottomRight(bottomRight);
    this.text=text;
  }


  public RectButton(int xLeft, int yTop, int width, int height, String text) {
    setTopLeft(new Point(xLeft,yTop));
    setBottomRight(new Point(xLeft+width-1,yTop+height-1));
    this.text=text;
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


  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  public boolean isActive() { return active; }
  public void setActive(boolean active) { this.active = active; }
  public Point getTopLeft() { return topLeft; }
  public void setTopLeft(Point topLeft) {
    this.topLeft = topLeft;
    xLeft = topLeft.getX();
    yTop = topLeft.getY(); }
  public Point getBottomRight() { return bottomRight; }
  public void setBottomRight(Point bottomRight) {
    this.bottomRight = bottomRight;
    width = getBottomRight().getX()-getTopLeft().getX()+1;
    height = getBottomRight().getY()-getTopLeft().getY()+1; }

  public int getWidth() {
    return width; }

  public int getHeight() {
    return height; }

  public void moveTo(int x, int y) {
    setTopLeft(new Point(x,y));
    setBottomRight(new Point(x+getWidth()-1,y+getHeight()-1));
  }

  public void moveTo(Point point) {
    setTopLeft(point);
    setBottomRight(new Point(point.getX()+getWidth()-1,point.getY()+getHeight()-1));
  }

  public void moveRel(int dx, int dy) {
    setTopLeft(new Point(topLeft.getX()+dx,topLeft.getY()+dy));
    setBottomRight(new Point(bottomRight.getX()+dx,bottomRight.getY()+dy));
  }

  public void resize(double ratio) {
    int newWidth = (int)((double)getWidth()*ratio);
    if (newWidth==0) {
      newWidth=1;
    }
    int newHeight = (int)((double)getHeight()*ratio);
    if (newHeight==0) {
      newHeight=1;
    }
    setBottomRight(new Point(topLeft.getX()+newWidth-1,topLeft.getY()+newHeight-1));
  }

  public boolean isInside(int x, int y) {
    if (xLeft <= x && x < (xLeft + width) && yTop <= y && y < (yTop + height)) {
      return true;
    }
    return false;
  }

  public boolean isInside(Point point) {
    if (xLeft<=point.getX() && point.getX()<(xLeft+width) && yTop<=point.getY() && point.getY()<(yTop+height)) {
      return true;
    }
    return false;
  }

  public boolean isIntersects(RectButton rectButton) {
    if (rectButton.getTopLeft().getX()+rectButton.width>getTopLeft().getX()&&
            rectButton.getTopLeft().getX()<getTopLeft().getX()+width&&
            rectButton.getTopLeft().getY()+rectButton.height>getTopLeft().getY()&&
            rectButton.getTopLeft().getY()<getTopLeft().getY()+width){
      return true;
    }
    return false;
  }

  public boolean isInside(RectButton rectButton) {
    if (rectButton.getTopLeft().getX()>=getTopLeft().getX()&&
            rectButton.getTopLeft().getX()+rectButton.width<=getTopLeft().getX()+width&&
            rectButton.getTopLeft().getY()>=getTopLeft().getY()&&
            rectButton.getTopLeft().getY()+rectButton.height<=getTopLeft().getY()+height){
      return true;
    }
    return false;
  }

  public boolean isFullyVisibleOnDesktop(Desktop desktop) {
    if ( topLeft.isVisibleOnDesktop(desktop)&& bottomRight.isVisibleOnDesktop(desktop)) {
      return true;
    }
    return false;
  }


}
