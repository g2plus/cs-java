package top.arhi.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo56 {
    public static void main(String[] args) {
        Character character = Character.valueOf('a');
        String s = character.toString(97);
        if (s.equals(character.toString())) {
            System.out.println(true);
        }
    }
}
