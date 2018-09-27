package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.WindowErrorCode;
import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

public class ComboBox extends ListBox {

    private Integer selected;

    public ComboBox (Point topLeft,
                    Point bottomRight,
                    WindowState windowState,
                    String[] lines,
                    Integer selected) throws WindowException {
        super(topLeft, bottomRight, windowState, lines);
        super.setLines(lines);
        setSelected(selected);
    }

    public ComboBox(Point topLeft,
                    Point bottomRight,
                    String stateString,
                    String[] lines,
                    Integer selected) throws  WindowException{
        super(topLeft, bottomRight, stateString, lines);
        super.setLines(lines);
        setSelected(selected);
    }

    public ComboBox(int xLeft,
                    int yTop,
                    int width,
                    int height,
                    WindowState windowState,
                    String[] lines,
                    Integer selected) throws WindowException {
        super(xLeft, yTop, width, height, windowState, lines);
        super.setLines(lines);
        setSelected(selected);
    }

    public ComboBox(int xLeft,
                    int yTop,
                    int width,
                    int height,
                    String stateString,
                    String[] lines,
                    Integer selected) throws WindowException {
        super(xLeft, yTop, width, height, stateString, lines);
        super.setLines(lines);
        setSelected(selected);
    }

    public ComboBox(Point topLeft,
                    Point bottomRight,
                    String[] lines,
                    Integer selected) throws  WindowException{
        super(topLeft, bottomRight, lines);
        super.setLines(lines);
        setSelected(selected);
    }

    public ComboBox(int xLeft,
                    int yTop,
                    int width,
                    int height,
                    String[] lines,
                    Integer selected) throws  WindowException{
        super(xLeft, yTop, width, height, lines);
        super.setLines(lines);
        setSelected(selected);
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) throws  WindowException{
        if(getLines()==null && selected!=null){
            throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        }
        if(selected!=null&&selected>getLines().length-1
                ||selected!=null&&selected<0){
            throw new WindowException(WindowErrorCode.WRONG_INDEX);
        }
        if(selected==null) {
            this.selected = null;
        }else {
            this.selected = selected;
        }
    }

    public void setLines(String[] lines) {
        super.setLines(lines);
        try {
            setSelected(null);
        } catch (WindowException e) {
            e.printStackTrace();
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ComboBox comboBox = (ComboBox) o;

        return selected != null ? selected.equals(comboBox.selected) : comboBox.selected == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (selected != null ? selected.hashCode() : 0);
        return result;
    }
}
