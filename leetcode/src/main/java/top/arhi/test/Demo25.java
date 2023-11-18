package top.arhi.test;

import top.arhi.enums.CommentType;

public class Demo25 {
    public static void main(String[] args) {
        CommentType like = CommentType.LEMON;
        System.out.println(like.getType());
        like.print();
    }
}
