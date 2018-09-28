package net.thumbtack.school.windows.v4;

import net.thumbtack.school.base.StringOperations;
import net.thumbtack.school.windows.v4.base.RectWindow;
import net.thumbtack.school.windows.v4.base.WindowErrorCode;
import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

import java.util.Arrays;

public class ListBox extends RectWindow {

    private String[] lines;

    public ListBox(Point topLeft,
                   Point bottomRight,
                   WindowState windowState,
                   String[] lines) throws WindowException {
        if (windowState == WindowState.DESTROYED) {
            throw new WindowException(WindowErrorCode.WRONG_STATE);
        }
        setTopLeft(topLeft);
        setBottomRight(bottomRight);
        setState(windowState);
        setLines(lines);
    }

    public ListBox(Point topLeft,
                   Point bottomRight,
                   String stateString,
                   String[] lines) throws WindowException {
        if (stateString != null && stateString.compareTo("DESTROYED") == 0) {
            throw new WindowException(WindowErrorCode.WRONG_STATE);
        }
        setTopLeft(topLeft);
        setBottomRight(bottomRight);
        setState(WindowState.fromString(stateString));
        setLines(lines);
    }


    public ListBox(int xLeft,
                   int yTop,
                   int width,
                   int height,
                   WindowState windowState,
                   String[] lines) throws WindowException {
        if (windowState == WindowState.DESTROYED) {
            throw new WindowException(WindowErrorCode.WRONG_STATE);
        }
        setTopLeft(new Point(xLeft, yTop));
        setBottomRight(new Point(xLeft + width - 1, yTop + height - 1));
        setState(windowState);
        setLines(lines);
    }

    public ListBox(int xLeft,
                   int yTop,
                   int width,
                   int height,
                   String stateString,
                   String[] lines) throws WindowException {
        if (stateString != null && stateString.compareTo("DESTROYED") == 0) {
            throw new WindowException(WindowErrorCode.WRONG_STATE);
        }
        setTopLeft(new Point(xLeft, yTop));
        setBottomRight(new Point(xLeft + width - 1, yTop + height - 1));
        setState(WindowState.fromString(stateString));
        setLines(lines);
    }

    public ListBox(Point topLeft,
                   Point bottomRight,
                   String[] lines) {
        setTopLeft(topLeft);
        setBottomRight(bottomRight);
        setLines(lines);
    }

    public ListBox(int xLeft,
                   int yTop,
                   int width,
                   int height,
                   String[] lines) {
        setTopLeft(new Point(xLeft, yTop));
        setBottomRight(new Point(xLeft + width - 1, yTop + height - 1));
        setLines(lines);
    }

    public String[] getLines() {
        if (this.lines == null) {
            return null;
        } else {
            String[] copyLines = new String[lines.length];
            System.arraycopy(lines, 0, copyLines, 0, lines.length);
            return copyLines;
        }
    }

    public void setLines(String[] lines) {
        if (lines == null) {
            this.lines = null;
        } else {
            this.lines = new String[lines.length];
            System.arraycopy(lines, 0, this.lines, 0, lines.length);
        }
    }

    public String[] getLinesSlice(int from, int to) throws WindowException {
        if (lines == null) {
            throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        }
        if (from < 0 || lines.length < to || from > to - 1) {
            throw new WindowException(WindowErrorCode.WRONG_INDEX);
        }
        int j = 0;
        String[] linesSlice = new String[to - from];
        for (int i = from; i < to; i++) {
            linesSlice[j] = lines[i];
            j++;
        }
        return linesSlice;
    }

    public String getLine(int index) throws WindowException {
        if (lines == null) {
            throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        }
        if (index >= lines.length || index < 0) {
            throw new WindowException(WindowErrorCode.WRONG_INDEX);
        } else return lines[index];
    }

    public void setLine(int index, String line) throws WindowException {
        if (lines == null) {
            throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        }
        if (index >= lines.length || index < 1) {
            throw new WindowException(WindowErrorCode.WRONG_INDEX);
        } else {
            lines[index - 1] = line;
        }
    }

    public Integer findLine(String line) {
        if (lines != null) {
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].equals(line)) {
                    return i;
                }
            }
        }
        return null;
    }

    public void reverseLineOrder() {
        if (lines != null) {
            int j = 0;
            String[] reverseLineOrder = new String[lines.length];
            for (int i = 0; i < lines.length; i++) {
                reverseLineOrder[j] = lines[lines.length - 1 - i];
                j++;
            }
            setLines(reverseLineOrder);
        }
    }

    public void reverseLines() {
        if (lines != null) {
            String[] reverseLines = new String[lines.length];
            int j = 0;
            for (String elem : lines) {
                reverseLines[j] = StringOperations.reverse(elem);
                j++;
            }
            setLines(reverseLines);
        }
    }

    public void duplicateLines() {
        if (lines != null) {
            String[] duplicateLines = new String[lines.length * 2];
            int j = 0;
            for (String elem : lines) {
                duplicateLines[j] = elem;
                duplicateLines[j + 1] = elem;
                j += 2;
            }
            setLines(duplicateLines);
        }
    }

    public void removeOddLines() {
        if (lines != null && lines.length > 1) {
            String[] removeOddLines = new String[(lines.length + 1) / 2];
            int j = 0;
            for (int i = 0; i < lines.length; i += 2) {
                removeOddLines[j] = lines[i];
                j++;
            }
            setLines(removeOddLines);
        }
    }

    public boolean isSortedDescendant() {
        if (lines == null || lines.length == 1) {
            return true;
        }
        for (int i = 1; i < lines.length; i++) {
            if (lines[i].compareTo(lines[i - 1]) < 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ListBox listBox = (ListBox) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(lines, listBox.lines);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(lines);
        return result;
    }
}
