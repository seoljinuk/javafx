package com.itgroup.jdbc;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;

import java.util.Scanner;

public class UpdateMain {
    public static void main(String[] args) {
        // 특정 상품에 대한 정보를 수정합니다.
        ProductDao dao = new ProductDao() ;
        Product bean = new Product();

        Scanner scan = new Scanner(System.in) ;
        System.out.print("상품 번호 : ");
        int pnum = scan.nextInt() ;

        System.out.print("상품 이름 : ");
        String name = scan.next() ;

        bean.setPnum(pnum);
        bean.setName(name);
        bean.setCompany("AB 식품");
        bean.setImage01("aa.png");
        bean.setImage02("bb.png");
        bean.setImage03("cc.png");
        bean.setStock(9999);
        bean.setPrice(1111);
        bean.setCategory("bread");
        bean.setContents("별로예요");
        bean.setPoint(15);
        bean.setInputdate("2024/07/17");

        int cnt = -1 ; // -1을 실패한 경우라고 가정합니다.
        cnt = dao.updateData(bean);

        if(cnt==-1){
            System.out.println("상품 수정에 실패하였습니다.");
        }else{
            System.out.println("상품 수정에 성공하였습니다.");
        }
    }
}
