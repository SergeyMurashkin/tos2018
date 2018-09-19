package net.thumbtack.school.windows.v2;

public class ComboBox extends ListBox {

    private Integer selected;

    public ComboBox(Point topLeft, Point bottomRight, boolean active, String[] lines, Integer selected) {
	super(topLeft, bottomRight, active, lines);
	this.selected = selected;
    }

    public ComboBox(int xLeft, int yTop, int width, int height, boolean active, String[] lines, Integer selected) {
	super(xLeft, yTop, width, height, active, lines);
	this.selected = selected;
    }

    public ComboBox(Point topLeft, Point bottomRight, String[] lines, Integer selected) {
	super(topLeft, bottomRight, lines);
	this.selected = selected;
    }

    public ComboBox(int xLeft, int yTop, int width, int height, String[] lines, Integer selected) {
	super(xLeft, yTop, width, height, lines);
	this.selected = selected;
    }

    public Integer getSelected() {
	return selected;
    }

    public void setSelected(Integer selected) {
	this.selected = selected;
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
