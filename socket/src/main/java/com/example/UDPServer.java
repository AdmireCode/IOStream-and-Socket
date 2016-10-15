package com.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Administrator on 2016/10/14 0014.
 * 服务器端，实现基于UDP的用户登陆
 */
public class UDPServer {
    public static void main(String[] args) throws IOException {
        /*
        接收客户端发送的数据
         */
        //1、创建服务器端DatagramSocket，指定端口
        DatagramSocket socket = new DatagramSocket(8800);
        //2、创建数据报，用于接受客户端发送的数据
        byte[] data = new byte[1024];//创建字节数组，指定接收数据包的大小
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
        //3、接受客户端发送的数据
        System.out.println("*** 服务器端已经启动，等待客户端发送数据 ***");
        socket.receive(datagramPacket);//此方法在接收到数据报之前会一直阻塞
        //4、读取数据
        String info = new String(data, 0, datagramPacket.getLength());
        System.out.println("我是服务器，客户端说：" + info);

        /*
        向客户端响应数据
         */
        //1、定义客户端的地址、端口号、数据
        InetAddress address = datagramPacket.getAddress();
        int port = datagramPacket.getPort();
        byte[] data2 = "欢迎您！".getBytes();
        //2、创建数据报，包含响应的数据信息
        DatagramPacket datagramPacket1 = new DatagramPacket(data2, data2.length, address, port);
        //3、响应客户端
        socket.send(datagramPacket1);
        //4、关闭资源
        socket.close();

    }
}