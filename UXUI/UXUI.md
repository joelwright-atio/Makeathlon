# UI/UX Guidence

You first need to research what a good Dashboard looks like.

You are primerily responsable for the asthetic of the digital product, but nevertheless, this must contain data that is of interest to the user.

Go through iterative designs to work out how best to package the product.

Here is a link to ThingWorx Commiunity guide and tutorial to building a mashup in ThingWorx:
https://community.ptc.com/t5/IoT-Tips/Create-Your-Application-UI-Guide-Part-1/ta-p/809008

## Prototyping & Figma

Figma is a great way to prototype interface ideas. If you have not used figma before, search for tutorials on youtube to see it used and to follow along with tutorials.

When you are ready to access the Atio ThingWorx Server, you need to use the Sophos Client VPN.

## Connect to VPN

This guide provides step-by-step instructions on how to connect to the Atio VPN using the Sophos Connect app. By following these simple steps, you can quickly establish a secure connection and access the Atio VPN network.

1\. Click "search"

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-18/10f63720-f79a-49f5-a0bb-8dc3e4f4c32b/ascreenshot.jpeg?tl_px=439,599&br_px=1299,1080&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=402,412)

2\. Search "Sophos Connect"

3\. Click here on utm.rueger-it.de

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-18/7ed47d3f-1679-4c5e-bd18-93be18fa2713/ascreenshot.jpeg?tl_px=0,0&br_px=859,480&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=290,201)

4\. Click "Connect"

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-18/a7900c68-71fb-4306-95ac-e078fe949ad5/ascreenshot.jpeg?tl_px=111,0&br_px=971,480&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=402,75)

5\. Username "makeathlon0X", where X is your computer number

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-18/8574f9ab-1de9-4194-a03a-19e1bfc8fac8/ascreenshot.jpeg?tl_px=0,99&br_px=859,580&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=276,212)

6\. Enter the password "$umM9Vavo" and click sign in

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-18/93f9814c-dda1-4499-a0c1-48ced512a57c/ascreenshot.jpeg?tl_px=0,224&br_px=859,705&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=288,212)

7\. Congratilations, you are signed in to the atio VPN!

Next step is to access the ThingWorx Server via your browser.

## Accessing the ThingWorx Server

This guide provides step-by-step instructions on how to sign into ThingWorx and create an application key. It includes details on entering the username and password, saving login information and selecting the project context.

1\. Start a browser and paste http://192.168.20.3:8080/Thingworx/Composer

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-18/45bbb490-5a1d-4ad0-bda7-2144fd21364e/ascreenshot.jpeg?tl_px=0,0&br_px=1719,961&force_format=png&width=1120.0&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=430,18)

2\. As long as the VPN is working correctly, when the page loads, a sign in box will show.

3\. In the sign in box, enter "makeathon" as username

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-18/1b26d6ae-5c8a-48c9-8040-1e6aa1bdc091/ascreenshot.jpeg?tl_px=459,42&br_px=1319,523&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=402,212)

4\. enter "atiozebramakeathon" as password

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-18/79e7bd62-8d7c-4682-a74c-1ed656f540e3/ascreenshot.jpeg?tl_px=469,114&br_px=1329,595&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=402,212)

5\. Finally click "Anmelden" to log in.

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-18/b0ade0c3-117d-4de1-8d49-8060fdb8c994/ascreenshot.jpeg?tl_px=536,203&br_px=1396,684&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=402,212)

6\. You can save the username and password in the browser so you do not have to repeat this step.

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-18/e6c445c6-e9be-460e-98d0-f11cf811aa2f/ascreenshot.jpeg?tl_px=0,0&br_px=1920,1080&force_format=png&width=1120.0&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=797,267)
