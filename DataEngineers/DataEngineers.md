# Data Engineers Guidence

First, you will need to unertand Modbus TCP connection.

https://www.youtube.com/watch?v=txi2p5_OjKU

You can first use Modbus Slave and qModMaster to simulate comminucation.

You will need to find the addresses for the communication via the instruction manual for the robot arm. You can find that on YouTube

You will then need to read the signals in Java, and work with the thingWorx backend Enginners to forward the signals from the robot on to the ThingWorx server.

## Reading from a Modbus Slave with QModMaster

I have a Modbus Slave client running on my laptop. I will give you the local IP address when you ask for it. The Modbus Poll is running on port 502.

1\. Click "Start"

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/e9026ccb-7a4a-4190-9bc4-213c4655f4b8/ascreenshot.jpeg?tl_px=444,599&br_px=1304,1080&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=402,415)

2\. Search for and select the QModMaster.exe

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/68e4b950-b7e8-4821-9e1c-8833e52429f3/ascreenshot.jpeg?tl_px=219,115&br_px=1079,596&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=402,212)

3\. Click "Modbus TCP"
This brings up the Setting box where you can enter the TCP port of 502 (default)
And change the IP address to the local ip address of my laptop. I will give you the IP adress when you ask for it.
Click "Modbus Mode" to change it from RTU to TCP
Click "TCP"
Change the "Function Code" drop down
Click "Read Holding Registers (0x03)"
Change the data ouput format from Binary to Decimal
Click "Dec"
Click "Connect"
Click "Scan"
Congratulations, You are reading data from my laptop via Modbus TCP!

![](https://colony-recorder.s3.amazonaws.com/files/2023-11-19/aa217ca3-528f-4e71-a558-25301d6b3f99/stack_animation.webp)

4\. Answer the following question: How would you access other Data being sent over the same Modbus slave channel. And how can you visulize multiple data points at the same time?

Answer: experiment in QModMaster with the Start Address and the Number of Registers.

## Reading data from the robot

With the robot manual, find the correct settings you require. Work with the Robot team to find the IP adress of the robot.

Hint: if the robot is not connected to a network, then you will need to use an Ethernet Cabel and networking bridge.

Test using QModMaster that you can read information from the machine.

## Java

Because the Client delivering data to ThingWorx leverages the Java ThingWorx SDK, it is important to read the signals not only in QModMaster, but also in Java.

1\. Click Start

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/bc15c2a8-326b-4e54-b0cb-6d85bf881006/ascreenshot.jpeg?tl_px=442,599&br_px=1302,1080&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=402,419)

2\. Search for and select the JetBrains Toolbox

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/71cb716c-e311-494c-9843-17787d5f04e0/ascreenshot.jpeg?tl_px=175,145&br_px=1035,626&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=402,212)

3\. Once the JetBrains Toolbox has loaded, select the Intellij. This is the Java IDE.

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/3105efe8-9cfd-45ef-aa5a-0ada6245e5c1/ascreenshot.jpeg?tl_px=1059,195&br_px=1919,676&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=402,212)

4\. On the project explorer pannel, find the TestModbus class, double click to select it.

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/fd8dbe02-c896-4c99-ae68-ddb18ef4252f/ascreenshot.jpeg?tl_px=0,0&br_px=1920,1080&force_format=png&width=1120.0&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=151,325)

5\. Note that the class contains a runnable main loop as well as a method called "readModbusTCP". Run the class/main method by firstly clicking the green arrow.

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/4d1710de-d99e-461e-bea9-9d8ed251d3ba/ascreenshot.jpeg?tl_px=0,0&br_px=1920,1080&force_format=png&width=1120.0&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=399,84)

6\. Then click "Run TestModbus.main()"

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/2413d8b9-73fe-4e53-8355-94adb845007f/ascreenshot.jpeg?tl_px=416,0&br_px=1276,480&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=402,200)

7\. The answer is printed to the console.

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/4cdbc9ab-f2df-4f54-8c8c-e259acd6ee27/ascreenshot.jpeg?tl_px=0,599&br_px=859,1080&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=166,220)

Tip: Spend some time understanding this code and comparing it to QModMaster. Make sure you understand what parameters you require before editing this code to read from the Robot.

## Saving Data

You need to be cognasant that the data you read will be required by the other groups.

You need to work with the ThingWorx Backend group so that data is fed into ThingWorx.

You also need to be cognasant that the Analytics group needs historical data.

You can do the latter by saving data to SQL.

If you have not done this before, Generative AI can really help you.

## How to Open the Microsoft SQL Server Management Studio

1\. Click "Start"

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/4cd7a40b-0d51-4fe3-8f34-373284f82e6f/ascreenshot.jpeg?tl_px=411,599&br_px=1271,1080&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=402,414)

2\. Click "Desktop"

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/786f731c-43d4-41b2-81d4-1edc6dd67e0f/ascreenshot.jpeg?tl_px=220,124&br_px=1080,605&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=402,212)

3\. Click "Connect"

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/2a03d318-2965-4c24-85d5-2e8234d122c1/ascreenshot.jpeg?tl_px=459,314&br_px=1319,795&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=402,212)

4\. Click "Databases"

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/07e7896a-16c2-46c6-8c33-6824a7891264/ascreenshot.jpeg?tl_px=0,54&br_px=859,535&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=14,212)

5\. Click "atio" database

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/0a63de8c-b986-4ddf-8d6b-2424c310b641/ascreenshot.jpeg?tl_px=0,137&br_px=859,618&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=33,212)

6\. Click "Tables"

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/ec5e7ebf-0143-4eb1-9ca9-17636a312bf6/ascreenshot.jpeg?tl_px=0,192&br_px=859,673&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=52,212)

7\. Right click "dbo.test"

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/6cca16d2-1c35-418c-adaa-c81bd5ced7b7/ascreenshot.jpeg?tl_px=0,336&br_px=859,817&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=114,212)

8\. Click "Select Top 1000 Rows"

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/54de8c24-a8ef-438b-8015-c0a099977eb5/ascreenshot.jpeg?tl_px=0,249&br_px=859,730&force_format=png&width=860&wat_scale=76&wat=1&wat_opacity=0.7&wat_gravity=northwest&wat_url=https://colony-recorder.s3.us-west-1.amazonaws.com/images/watermarks/FB923C_standard.png&wat_pad=209,212)

9\. This shows the SQL query in the top pannel and the result of the query the bottom.

![](https://ajeuwbhvhr.cloudimg.io/colony-recorder.s3.amazonaws.com/files/2023-11-19/7f61c8a9-ff4b-4237-af20-df012f41824c/user_cropped_screenshot.jpeg?tl_px=0,0&br_px=1920,1080&force_format=png&width=1120.0)

Next Steps are to learn to use SQL databases by creating your own tables, adding data to the tables, reading data to the tables, how to edit/update data in the tables and learning how to delete data in the tables.

## SQL guide:

Inside your database, right-click on "Tables" and select "New Table." Define the columns, data types, and constraints for your table. Use the following template:

```sql
CREATE TABLE TableName
(
    Column1 DataType1,
    Column2 DataType2,
    ...

);

```

It is important to undertand what datatype you need:

| Data Type     | Description                              |
| ------------- | ---------------------------------------- |
| INT           | Integer                                  |
| SMALLINT      | Small integer                            |
| BIGINT        | Big integer                              |
| FLOAT         | Floating-point number                    |
| DOUBLE        | Double-precision floating-point number   |
| DECIMAL(p, s) | Fixed-point decimal                      |
| CHAR(n)       | Fixed-length character string            |
| VARCHAR(n)    | Variable-length character string         |
| TEXT          | Variable-length character string (large) |
| DATE          | Date                                     |
| TIME          | Time                                     |
| DATETIME      | Date and time                            |
| BOOLEAN       | Boolean                                  |
| BINARY        | Fixed-length binary data                 |
| VARBINARY     | Variable-length binary data              |
| BLOB          | Binary large object (large binary data)  |

When you need a column with a string, the go to is `varchar(50)` for short strings. However, if your string exceeds 50 characters this will trigger an error.

6. Inserting Data:
   Once your table is created, you can add data using the

```sql
INSERT INTO statement:

INSERT INTO TableName (Column1, Column2, ...)
VALUES (Value1, Value2, ...);
```

7. Reading Data:
   To retrieve data from a table, use the SELECT statement:

```sql
SELECT * FROM TableName;
```

You can also specify conditions and filter data:

```sql
SELECT Column1, Column2 FROM TableName WHERE Condition;
```

8. Updating Data:
   To modify existing data, use the UPDATE statement:

```sql
UPDATE TableName
SET Column1 = NewValue1, Column2 = NewValue2
WHERE Condition;
```

9. Deleting Data:

To remove data, use the DELETE statement:

```sql
DELETE FROM TableName WHERE Condition;
```

## Java Database connector

You will need to write to the database, not from the management studio, but instead from a java program where the ModBus TCP signals can be read.
