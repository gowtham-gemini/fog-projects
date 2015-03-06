package com.kayako.api.test;

/*
Copyright (c) 2013 Kayako

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/

import java.util.ArrayList;
import com.kayako.api.enums.HttpResponseTypeEnum;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.util.library.RESTClient;

public class TestAPI extends KEntity{
	/**
	 * The Controller.
	 */
	static protected String controller = "/Core/TestAPI";
	
	/**
	 * To Check the API is working.
	 * 
	 * @throws Exception
	 */
	public static void Check() throws Exception 
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("GetData");
		System.out.println("GET Request Test Results \n");
		System.out.println(((RESTClient) KEntity.getRESTClient()).setResponseType(HttpResponseTypeEnum.PLAIN).get(controller, list).toString());
		
		list.clear();
		list.add("PostData");
		System.out.println("\n POST Request Test Results \n");
		System.out.println(((RESTClient) KEntity.getRESTClient()).setResponseType(HttpResponseTypeEnum.PLAIN).post(controller, list).toString());
	}

	@Override
	public KEntity populate(RawArrayElement element) throws KayakoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
}