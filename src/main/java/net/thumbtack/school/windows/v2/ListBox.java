package net.thumbtack.school.windows.v2;

import net.thumbtack.school.base.StringOperations;

import java.util.Arrays;

public class ListBox {
  private int xLeft;
  private int yTop;
  private int width;
  private int height;
  private Point topLeft;
  private Point bottomRight;
  private boolean active = true;
  private String[] lines;

  public ListBox(Point topLeft, Point bottomRight, boolean active, String[] lines){
    setTopLeft(topLeft);
    setBottomRight(bottomRight);
    this.active = active;
    setLines(lines);
  }

  public ListBox(int xLeft, int yTop, int width, int height, boolean active, String[] lines){
    setTopLeft(new Point(xLeft,yTop));
    setBottomRight(new Point(xLeft+width-1,yTop+height-1));
    this.active = active;
    setLines(lines);
  }

  public ListBox(Point topLeft, Point bottomRight,  String[] lines){
    setTopLeft(topLeft);
    setBottomRight(bottomRight);
    setLines(lines);
  }

  public ListBox(int xLeft, int yTop, int width, int height, String[] lines){
    setTopLeft(new Point(xLeft,yTop));
    setBottomRight(new Point(xLeft+width-1,yTop+height-1));
    setLines(lines);
  }

  public Point getTopLeft() { return topLeft; }
  public void setTopLeft(Point topLeft) {
    this.topLeft = topLeft;
    xLeft = topLeft.getX();
    yTop = topLeft.getY();
  }
  public Point getBottomRight() { return bottomRight; }
  public void setBottomRight(Point bottomRight) {
    this.bottomRight = bottomRight;
    width = getBottomRight().getX()-getTopLeft().getX()+1;
    height = getBottomRight().getY()-getTopLeft().getY()+1;
  }
  public boolean isActive() { return active; }
  public void setActive(boolean active) { this.active = active; }

  public int getWidth() { return width; }
  public int getHeight() { return height; }

  public String[] getLines() {
    if (this.lines == null) {
      return null;
    }else {
      String[] copyLines = new String[lines.length];
      System.arraycopy(lines, 0, copyLines, 0, lines.length);
      return copyLines;
    }
  }

  public void setLines(String[] lines){
    if (lines == null) {
      this.lines=null;
    } else {
      this.lines = new String[lines.length] ;
      System.arraycopy(lines,0,this.lines,0,lines.length);
    }
  }

  public String[] getLinesSlice(int from, int to){
    if (lines==null) {
      return null;
    }
    if (to>lines.length) {
      to=lines.length;
    }
    int j=0;
    String[] linesSlice = new String[to-from];
    for (int i=from; i<to; i++){
      linesSlice[j]=lines[i];
      j++;
    }
    return linesSlice;
  }

  public String getLine(int index){
    if (lines==null){
      return null;
    }
    if(index>=lines.length){
      return null;
    }
    return lines[index];
  }

  public void setLine(int index, String line) {
   if (lines!=null && index<=lines.length) {
     lines[index-1]=line;
   }
  }

  public Integer findLine(String line) {
    if (lines != null) {
      for (int i = 0; i < lines.length; i++) {
        if (lines[i].equals(line)) {
          return new Integer(i);
        }
      }
    }
    return null;
  }

/*  public void reverseLineOrder(){
    if (lines!=null) {
      for (int i = 0; i < lines.length / 2; i++) {
        String swap = lines[i];
        lines[i] = lines[lines.length - i - 1];
        lines[lines.length - i - 1] = swap;
      }
    }
  }*/

  public void reverseLineOrder(){
    if (lines!=null) {
      int j=0;
      String[] reverseLineOrder = new String[lines.length];
      for (int i=0; i<lines.length; i++) {
        reverseLineOrder[j] = lines[lines.length-1-i];
        j++;
      }
      setLines(reverseLineOrder);
    }
  }

  public void reverseLines() {
    if (lines != null) {
      String[] reverseLines = new String[lines.length];
      int j = 0;
      for (int i = 0; i < lines.length; i++) {
        reverseLines[j] = StringOperations.reverse(lines[i]);
        j++;
      }
      setLines(reverseLines);
    }
  }

  public void duplicateLines() {
    if (lines != null) {
      String[] duplicateLines = new String[lines.length * 2];
      int j = 0;
      for (int i = 0; i < lines.length; i++) {
        duplicateLines[j] = lines[i];
        duplicateLines[j + 1] = lines[i];
        j += 2;
      }
      setLines(duplicateLines);
    }
  }

  public void removeOddLines(){
    if (lines!=null && lines.length>1) {
      String[] removeOddLines = new String[(lines.length+1)/2];
      int j=0;
      for (int i=0; i<lines.length; i+=2){
        removeOddLines[j] = lines[i];
        j++;
      }
      setLines(removeOddLines);
    }
  }

  public boolean isSortedDescendant(){
    if (lines==null||lines.length==1) {
      return true;
    }
    for (int i = 1; i < lines.length; i++) {
        if (lines[i].compareTo(lines[i - 1])<0){
          return  true;
        }
      }
      return false;
    }


  public void moveTo(int x, int y){
    setTopLeft(new Point(x,y));
    setBottomRight(new Point(x+getWidth()-1,y+getHeight()-1));
  }

  public void moveTo(Point point){
    setTopLeft(point);
    setBottomRight(new Point(point.getX()+getWidth()-1,point.getY()+getHeight()-1));
  }

  public void moveRel(int dx, int dy){
    setTopLeft(new Point(topLeft.getX()+dx,topLeft.getY()+dy));
    setBottomRight(new Point(bottomRight.getX()+dx,bottomRight.getY()+dy));
  }

  public void resize(double ratio){
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

  public boolean isIntersects(ListBox listBox) {
   if (listBox.getTopLeft().getX()+listBox.width>getTopLeft().getX()&&
           listBox.getTopLeft().getX()<getTopLeft().getX()+width&&
           listBox.getTopLeft().getY()+listBox.height>getTopLeft().getY()&&
            listBox.getTopLeft().getY()<getTopLeft().getY()+width){
     return true;
   }
    return false;
  }

/* public boolean isIntersects(ListBox listBox) {
    Point lT = new Point(listBox.xLeft,listBox.yTop);
    Point rT = new Point(listBox.xLeft+listBox.width-1,listBox.yTop);
    Point lB = new Point(listBox.xLeft,listBox.yTop+listBox.height-1);
    Point rB = new Point(listBox.xLeft+listBox.width-1,listBox.yTop+listBox.height-1);

    Point lT2 = new Point(xLeft,yTop);
    Point rT2 = new Point(xLeft+width-1,yTop);
    Point lB2 = new Point(xLeft,yTop+height-1);
    Point rB2 = new Point(xLeft+width-1,yTop+height-1);

    if (isInside(lT)||isInside(rT)||isInside(lB)||isInside(rB)||
            listBox.isInside(lT2)|| listBox.isInside(rT2)|| listBox.isInside(lB2)|| listBox.isInside(rB2)) {
      return true;
    }
    return false;
  }
*/


  public boolean isInside(ListBox listBox) {
    if (listBox.getTopLeft().getX()>=getTopLeft().getX()&&
            listBox.getTopLeft().getX()+listBox.width<=getTopLeft().getX()+width&&
            listBox.getTopLeft().getY()>=getTopLeft().getY()&&
            listBox.getTopLeft().getY()+listBox.height<=getTopLeft().getY()+height){
      return true;
    }
    return false;
  }


/*  public boolean isInside(ListBox listBox) {
    Point lT = new Point(listBox.xLeft,listBox.yTop);
    Point rT = new Point(listBox.xLeft+listBox.width-1,listBox.yTop);
    Point lB = new Point(listBox.xLeft,listBox.yTop+listBox.height-1);
    Point rB = new Point(listBox.xLeft+listBox.width-1,listBox.yTop+listBox.height-1);

    if (isInside(lT)&&isInside(rT)&&isInside(lB)&&isInside(rB)) {
      return true;
    }
    return false;
  }*/


  public boolean isFullyVisibleOnDesktop(Desktop desktop) {

    if ( topLeft.isVisibleOnDesktop(desktop)&& bottomRight.isVisibleOnDesktop(desktop)) {
      return true;
    }
    return false;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ListBox listBox = (ListBox) o;

    if (xLeft != listBox.xLeft) return false;
    if (yTop != listBox.yTop) return false;
    if (width != listBox.width) return false;
    if (height != listBox.height) return false;
    if (active != listBox.active) return false;
    if (topLeft != null ? !topLeft.equals(listBox.topLeft) : listBox.topLeft != null) return false;
    if (bottomRight != null ? !bottomRight.equals(listBox.bottomRight) : listBox.bottomRight != null) return false;
    // Probably incorrect - comparing Object[] arrays with Arrays.equals
    return Arrays.equals(lines, listBox.lines);
  }

  @Override
  public int hashCode() {
    int result = xLeft;
    result = 31 * result + yTop;
    result = 31 * result + width;
    result = 31 * result + height;
    result = 31 * result + (topLeft != null ? topLeft.hashCode() : 0);
    result = 31 * result + (bottomRight != null ? bottomRight.hashCode() : 0);
    result = 31 * result + (active ? 1 : 0);
    result = 31 * result + Arrays.hashCode(lines);
    return result;
  }
}
