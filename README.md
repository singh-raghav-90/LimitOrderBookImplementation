# LimitOrderBookImplementation
Overview:
This project  implements limit order book(LOB) based on Price/Time Priority. Orders are submitted to this program in the form of csv file that contains details in the following format.

e.g: Order ID, direction, order time, lot or quantity, limit price, order type, ticker
O110,buy,2022-04-06 12:02:36,200,33.75,new,AAPL

StartExecutingLimitOrder is the entry point of the application that continuously watch a directory e.g: C:\LimitOrderBooking. In this directory, you will place all the limit order csv files like order2.csv, order3.csv and so on. In the same directory, you can also place the csv file that contains the order type as cancel. All the order in this sample project is stored in a nested treemap. For every update in the map whether new or cancel or reject, it will always update the nested map and display the output on the console.
