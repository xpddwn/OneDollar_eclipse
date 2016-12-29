package com.onedollar.dal;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Parcel;
import android.os.Parcelable;

import com.onedollar.util.Util;

public class TextEntity extends ToString implements Parcelable {

	private String mId = null;
	private String mText = null;

	public TextEntity() {

	}

	public TextEntity(String id, String text) {
		setId(id);
		setText(text);
	}

	public TextEntity(String str) {
		setId(null);
		setText(str);
	}

	public String getId() {
		return mId;
	}

	public void setId(String id) {
		this.mId = id;
	}

	public String getText() {
		return mText;
	}

	public void setText(String text) {
		this.mText = text;
	}

	public static TextEntity create(HashMap<String, Object> hashMap,
			String[] keys) {
		if (keys.length > 1) {
			return new TextEntity(Util.getValue(hashMap, keys[0]),
					Util.getValue(hashMap, keys[1]));
		}
		return null;
	}

	public static ArrayList<TextEntity> create(
			ArrayList<HashMap<String, Object>> hashMaps, String[] keys) {
		ArrayList<TextEntity> arrayList = new ArrayList<TextEntity>();
		if (hashMaps != null) {
			for (HashMap<String, Object> hashMap : hashMaps) {
				TextEntity entity = create(hashMap, keys);
				if (entity != null) {
					arrayList.add(entity);
				}
			}
		}

		return arrayList;
	}

	public static void addList(ArrayList<TextEntity> target,
			ArrayList<TextEntity> source) {
		if (source == null) {
			return;
		}
		if (target == null) {
			target = new ArrayList<TextEntity>();
		}
		for (TextEntity map : source) {
			target.add(map);
		}

	}

	@Override
	public int describeContents() {

		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mId);
		dest.writeString(mText);
	}

	public static final Parcelable.Creator<TextEntity> CREATOR = new Parcelable.Creator<TextEntity>() {
		public TextEntity createFromParcel(Parcel in) {
			TextEntity textEntity = new TextEntity();
			textEntity.setId(in.readString());
			textEntity.setText(in.readString());
			return textEntity;
		}

		public TextEntity[] newArray(int size) {
			return new TextEntity[size];
		}
	};

	public String toString() {
		return "[mId=" + mId + ",mText=" + mText + "]";
	};
}
