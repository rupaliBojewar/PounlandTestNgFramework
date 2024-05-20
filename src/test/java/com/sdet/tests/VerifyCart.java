package com.sdet.tests;

import com.sdet.AutomationBase.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VerifyCart extends Base {
    @Test
    public void verifyCartItems() throws IOException, InterruptedException {
        homePage.signInAccount();
        mainPage.addItemsInCart();

        List<String> itemsToVerify = new ArrayList<String>();
        for (String str : mainPage.readItemsFromExcel("cartItem")) {
            itemsToVerify.add(str);
        }
        Collections.sort(itemsToVerify);
        System.out.println(itemsToVerify);

        mainPage.moveToCart();

        List<String> cartItems = new ArrayList<String>();
        for (String str : cartPage.itemsFromCart()) {
            cartItems.add(str);
        }
        Collections.sort(cartItems);
        System.out.println(cartItems);

        Assert.assertEquals(itemsToVerify, cartItems);

        cartPage.removeItemsFromCart();
        mainPage.applicationLogOut();

    }
}
