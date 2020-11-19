package com.pandas.guardianshipassistant.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.pandas.guardianshipassistant.utils.ToastUtils;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

/**
 *类功能： 
 *@创建者 青鹏
 * @QQ    260498491
 *@date 2020/11/13 23:17
 */
public class PushService extends Service {
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		System.out.println("--------------PushService- onCreate-------------");

		IMService.conn.addPacketListener(new PacketListener() {
			@Override
			public void processPacket(Packet packet) {
				Message message = (Message) packet;
				String MESSAGE = message.getBody();
				System.out.println("MESSAGE"+MESSAGE);

				ToastUtils.showToastSafe(getApplicationContext(), MESSAGE);
			}
		}, null);
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		System.out.println("--------------PushService- onDestroy-------------");
		super.onDestroy();
	}
}
