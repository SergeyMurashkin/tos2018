package net.thumbtack.school.file;


import net.thumbtack.school.windows.v4.RectButton;
import net.thumbtack.school.windows.v4.base.WindowException;

import java.io.*;

public class FileService {

    public static void writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException {
        try (FileOutputStream bos = new FileOutputStream(fileName)) {
            bos.write(array);
        } catch (IOException ex) {
            throw ex;
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
        } catch (IOException ex) {
            throw ex;
        }

    }

    public static byte[] readByteArrayFromBinaryFile(File file) throws IOException {
        return FileService.readByteArrayFromBinaryFile(file.getPath());
    }

    public static byte[] writeAndReadByteArrayUsingByteStream(byte[] array) throws IOException {
        try {
            ByteArrayOutputStream baOut = new ByteArrayOutputStream(array.length);
            baOut.write(array);
            baOut.close();
            ByteArrayInputStream baIn = new ByteArrayInputStream(baOut.toByteArray());
            byte[] evenArray = new byte[(baIn.available() + 1) / 2];
            for (int i=0; i<evenArray.length; i++) {
                evenArray[i] = (byte) baIn.read();
                baIn.skip(1);
            }
            return evenArray;
        } catch (IOException ex) {
            throw ex;
        }
    }

    public static void  writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException {
        try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(fileName)))){
            bos.write(array);
        }catch (IOException ex){
            throw ex;
        }
    }

    public static void  writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        FileService.writeByteArrayToBinaryFileBuffered(file.getPath(),array);
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {
            int a = 0;
            while ((a = bis.read()) != -1) {
                bos.write(a);
            }
            return bos.toByteArray();
        } catch (IOException ex) {
            throw ex;
        }
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
        return FileService.readByteArrayFromBinaryFileBuffered(file.getPath());
    }

    public static void  writeRectButtonToBinaryFile(File file, RectButton rectButton) throws IOException {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))){
            dos.write(rectButton.getTopLeft().getX());
            dos.write(rectButton.getTopLeft().getY());
            dos.write(rectButton.getHeight());
            dos.write(rectButton.getWidth());
            dos.writeUTF(rectButton.getState().toString());
            dos.writeUTF(rectButton.getText());
        }catch (IOException ex){
            throw ex;
        }
    }

  public static RectButton readRectButtonFromBinaryFile(File file) throws IOException, WindowException {
        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))){
            return new RectButton(dis.read(),dis.read(),dis.read(),dis.read(),dis.readUTF(),dis.readUTF());
        }catch(IOException ex){
            throw ex;
        }
    }


}
