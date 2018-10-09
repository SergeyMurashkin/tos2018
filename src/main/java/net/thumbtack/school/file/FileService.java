package net.thumbtack.school.file;


import com.google.gson.Gson;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;
import net.thumbtack.school.windows.v4.Point;
import net.thumbtack.school.windows.v4.RectButton;
import net.thumbtack.school.windows.v4.base.WindowException;

import java.io.*;

public class FileService {

    public static void writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException {
        try (FileOutputStream bos = new FileOutputStream(fileName)) {
            bos.write(array);
        }
    }

    public static void writeByteArrayToBinaryFile(File file, byte[] array) throws IOException {
        FileService.writeByteArrayToBinaryFile(file.getPath(), array);
    }

    public static byte[] readByteArrayFromBinaryFile(String fileName) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             FileInputStream bis = new FileInputStream(fileName)) {
            int a = 0;
            while ((a = bis.read()) != -1) {
                bos.write(a);
            }
            return bos.toByteArray();
        }

    }

    public static byte[] readByteArrayFromBinaryFile(File file) throws IOException {
        return FileService.readByteArrayFromBinaryFile(file.getPath());
    }

    public static byte[] writeAndReadByteArrayUsingByteStream(byte[] array) throws IOException {
        ByteArrayOutputStream baOut = new ByteArrayOutputStream(array.length);
        baOut.write(array);
        ByteArrayInputStream baIn = new ByteArrayInputStream(baOut.toByteArray());
        byte[] evenArray = new byte[(baIn.available() + 1) / 2];
        for (int i = 0; i < evenArray.length; i++) {
            evenArray[i] = (byte) baIn.read();
            baIn.skip(1L);
        }
        return evenArray;
    }

    public static void writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName))) {
            bos.write(array);
        }
    }

    public static void writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        FileService.writeByteArrayToBinaryFileBuffered(file.getPath(), array);
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {
            int a = 0;
            while ((a = bis.read()) != -1) {
                bos.write(a);
            }
            return bos.toByteArray();
        }
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
        return FileService.readByteArrayFromBinaryFileBuffered(file.getPath());
    }

    public static void writeRectButtonToBinaryFile(File file, RectButton rectButton) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(rectButton.getTopLeft().getX());
            dos.writeInt(rectButton.getTopLeft().getY());
            dos.writeInt(rectButton.getBottomRight().getX());
            dos.writeInt(rectButton.getBottomRight().getY());
            dos.writeUTF(rectButton.getState().toString());
            dos.writeUTF(rectButton.getText());
        }
    }

    public static RectButton readRectButtonFromBinaryFile(File file) throws IOException, WindowException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            return new RectButton(new Point(dis.readInt(), dis.readInt()), new Point(dis.readInt(), dis.readInt()), dis.readUTF(), dis.readUTF());
        }
    }

    public static void writeRectButtonArrayToBinaryFile(File file, RectButton[] rects) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            for (RectButton elem : rects) {
                dos.writeInt(elem.getTopLeft().getX());
                dos.writeInt(elem.getTopLeft().getY());
                dos.writeInt(elem.getBottomRight().getX());
                dos.writeInt(elem.getBottomRight().getY());
            }
        }
    }
/*
    public static void modifyRectButtonArrayInBinaryFile(File file) throws IOException, WindowException {
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        RectButton[] rectButtons = new RectButton[(int) file.length() / 16];
        for (int i = 0; i < rectButtons.length; i++) {
            rectButtons[i] = new RectButton(new Point(dis.readInt() + 1, dis.readInt()), new Point(dis.readInt() + 1, dis.readInt()), "ACTIVE", "OK");
        }
        dis.close();
      FileService.writeRectButtonArrayToBinaryFile(file,rectButtons);
    }*/

    public static void modifyRectButtonArrayInBinaryFile(File file) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            for (long i = 0; i < file.length(); i += 8) {
                int x = 0;
                raf.seek(i);
                x = raf.readInt();
                raf.seek(i);
                raf.writeInt(x + 1);
            }
        }
    }

    public static RectButton[] readRectButtonArrayFromBinaryFile(File file) throws IOException, WindowException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            RectButton[] rectButtons = new RectButton[(int) file.length() / 16];
            for (int i = 0; i < rectButtons.length; i++) {
                rectButtons[i] = new RectButton(new Point(dis.readInt(), dis.readInt()),
                        new Point(dis.readInt(), dis.readInt()),
                        "ACTIVE", "OK");
            }
            return rectButtons;
        }
    }

    public static void writeRectButtonToTextFileOneLine(File file, RectButton rectButton) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            String oneString = rectButton.getTopLeft().getX() + " " +
                    rectButton.getTopLeft().getY() + " " +
                    rectButton.getBottomRight().getX() + " " +
                    rectButton.getBottomRight().getY() + " " +
                    rectButton.getState().toString() + " " +
                    rectButton.getText();
            dos.writeUTF(oneString);
        }
    }

    public static RectButton readRectButtonFromTextFileOneLine(File file) throws IOException, WindowException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            String oneString = dis.readUTF();
            String[] strings = oneString.split(" ");

            return new RectButton(new Point(Integer.parseInt(strings[0]), Integer.parseInt(strings[1])),
                    new Point(Integer.parseInt(strings[2]), Integer.parseInt(strings[3])),
                    strings[4],
                    strings[5]);
        }
    }

    public static void writeRectButtonToTextFileSixLines(File file, RectButton rectButton) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeUTF(rectButton.getTopLeft().getX() +
                    "\n" + rectButton.getTopLeft().getY() +
                    "\n" + rectButton.getBottomRight().getX() +
                    "\n" + rectButton.getBottomRight().getY() +
                    "\n" + rectButton.getState().toString() +
                    "\n" + rectButton.getText());
        }
    }

    public static RectButton readRectButtonFromTextFileSixLines(File file) throws WindowException, IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            String oneString = dis.readUTF();
            String[] strings = oneString.split("\n");

            return new RectButton(new Point(Integer.parseInt(strings[0]), Integer.parseInt(strings[1])),
                    new Point(Integer.parseInt(strings[2]), Integer.parseInt(strings[3])),
                    strings[4], strings[5]);
        }
    }

    public static void writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            String oneString = trainee.getFirstName() + " " + trainee.getLastName() + " " + trainee.getRating();
            dos.writeUTF(oneString);
        }
    }

    public static Trainee readTraineeFromTextFileOneLine(File file) throws IOException, TrainingException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            String oneString = dis.readUTF();
            String[] strings = oneString.split(" ");

            return new Trainee(strings[0], strings[1], Integer.parseInt(strings[2]));
        }
    }

    public static void writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            String oneString = trainee.getFirstName() + "\n" + trainee.getLastName() + "\n" + trainee.getRating();
            dos.writeUTF(oneString);
        }
    }

    public static Trainee readTraineeFromTextFileThreeLines(File file) throws TrainingException, IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            String oneString = dis.readUTF();
            String[] strings = oneString.split("\n");

            return new Trainee(strings[0], strings[1], Integer.parseInt(strings[2]));
        }
    }

    public static void serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new DataOutputStream(new FileOutputStream(file)))) {
            oos.writeObject(trainee);
        }
    }

    public static Trainee deserializeTraineeFromBinaryFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new DataInputStream(new FileInputStream(file)))) {
            return (Trainee) ois.readObject();
        }
    }

    public static String serializeTraineeToJsonString(Trainee trainee) {
        return new Gson().toJson(trainee);
    }

    public static Trainee deserializeTraineeFromJsonString(String json) {
        return new Gson().fromJson(json, Trainee.class);
    }

    public static void serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeUTF(FileService.serializeTraineeToJsonString(trainee));
        }
    }

    public static Trainee deserializeTraineeFromJsonFile(File file) throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            return FileService.deserializeTraineeFromJsonString(dis.readUTF());
        }
    }


}
