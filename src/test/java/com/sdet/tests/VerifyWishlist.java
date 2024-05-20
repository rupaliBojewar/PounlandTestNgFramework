package com.sdet.tests;

import com.sdet.AutomationBase.Base;
import com.sdet.pages.HomePage;
import com.sdet.pages.MainPage;
import com.sdet.pages.SignInPage;
import com.sdet.pages.WishListPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VerifyWishlist extends Base {


    @Test
    public void verifyWishlist() throws InterruptedException, IOException {

        homePage.signInAccount();
        mainPage.addItemInWishlist();
        Thread.sleep(5000);
        mainPage.moveToWishListPage();

        List<String> itemsToVerify = new ArrayList<String>();
        for (String str : mainPage.readItemsFromExcel("wishlistItem")) {
            itemsToVerify.add(str);
        }
        Collections.sort(itemsToVerify);
        System.out.println(itemsToVerify);

        List<String> wishlistItems1 = new ArrayList<String>();
        for (String str : wishListPage.itemsFromWishList()) {
            wishlistItems1.add(str);
        }
        Collections.sort(wishlistItems1);
        System.out.println(wishlistItems1);

        Assert.assertEquals(itemsToVerify, wishlistItems1);

        //Assert.assertTrue(itemsToVerify.contains(wishlistItems1));

        wishListPage.removeItemsFromWishList();
        mainPage.applicationLogOut();
    }

}
