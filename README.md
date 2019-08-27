# DreamTools
通用工具类
==========================================================================================================================================
1、如何使用

  Step 1.Add it in your root build.gradle at the end of repositories:
  
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
  Step 2. Add the dependency
  
       dependencies {
            implementation 'com.github.pilisiyadream:DreamTools:v1.0.5'
       }
   
   
  Step 3.use it in your project
  
      DreamDialogFactory.showTipsMessage(MainActivity.this, "提示", "倒计时弹窗提示信息！", 10000, false);
      
 
   
       
