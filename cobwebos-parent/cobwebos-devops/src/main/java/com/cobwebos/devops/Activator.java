/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cobwebos.devops;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	public void start(BundleContext context) {
		System.out.println("Starting the devops bundle");
		doStartJenkins();
	}

	public void stop(BundleContext context) {
		System.out.println("Stopping the devops bundle");
		doStopJenkins();
	}

	public void doStartJenkins() {
		System.out.println("Starting the devops platform");
		String command = "./bin/start_devops.sh";
		doCmd(command);
		
	}

	public void doStopJenkins() {
		System.out.println("Stopping the devops platform");
		String command = "./bin/stop_devops.sh";
		doCmd(command);

	}
	
	public void doCmd(String command) {
		
		Runtime runtime = Runtime.getRuntime();
		String line =null;
		try {
			Process p = runtime.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while((line=br.readLine())!=null) {
				System.out.println("cli>"+line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}