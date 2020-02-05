/*
 * Copyright (c) 2020 ZOLOZ PTE.LTD.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zoloz.api.sdk;

import com.alibaba.fastjson.JSON;
import com.zoloz.api.sdk.api.AuthTestAPI;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

/**
 * AuthTestAPITest
 *
 * @Author: jushi
 * @Date: 2020-01-22 17:13
 */
public class AuthTestAPITest extends ApiBaseTest{

    @Data
    public static class Message {
        private String title;
    }

    @Test
    public void testAuth(){
        Message request=new Message();
        request.setTitle("test");
        AuthTestAPI authTestAPI = new AuthTestAPI(getClient());
        Message response = authTestAPI.echo(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertEquals("test", response.getTitle());
    }


}