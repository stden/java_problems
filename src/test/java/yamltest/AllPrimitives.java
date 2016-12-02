package yamltest;

import java.util.Date;

/**
 * Разбор всех случаев
 * Пример класса, в котором есть поля всех примитивных типов
 */
public class AllPrimitives {
    public int publicInt = 3;
    public String str;
    public Date date;
    protected int protectedInt = 2;
    byte aByte = 10;
    Byte aByte2 = 5;
    short aShort = 111;
    int aInt = 1213;
    long aLong = 1225324234;
    float aFloat = 21432.1f;
    double aDouble = 12345.5678;
    char aChar = 'A';
    boolean aBoolean = true;
    private int privateInt = 1;

    public int getPrivateInt() {
        return privateInt;
    }

    public void setPrivateInt(int privateInt) {
        this.privateInt = privateInt;
    }
}