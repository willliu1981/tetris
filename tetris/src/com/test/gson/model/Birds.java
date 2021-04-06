package com.test.gson.model;

import java.util.HashMap;
import java.util.Map;



public abstract class Birds {

	public static class Name {
		private String name;

		public Name(String name) {
			this.name = name;
		}
		
		/*
		 * get and set
		 */

		public String getName() {
			return name;
		}

		public void setName(String firstName) {
			name = firstName;
		}

		public String toString() {
			return String.format("%s", this.name);
		}
	}

	public static class FullName extends Name {
		private String lastName;

		public FullName(String firstName, String lastName) {
			super(firstName);
			this.lastName = lastName;
		}
		
		/*
		 * get and set
		 */

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public void setFirstName(String firstName) {
			super.setName(firstName);
		}

		public String getFirstName() {
			return super.getName();
		}

		public String toString() {
			return String.format("%s %s", this.getFirstName(), this.lastName);
		}

	}

	public enum NameInfoType {
		Master, Self
	}

	private Map<Enum<NameInfoType>, Name> mapNameInfo = new HashMap<>();// <type,name>

	/*
	 * get and set
	 */
	public void setNameInfo(Name name, NameInfoType type) {
		this.mapNameInfo.put(type, name);
	}

	public Name getName(NameInfoType type) {
		return mapNameInfo.get(type);
	}

	public String toString() {
		return String.format("%s::infoMap->%s", this.getClass().getClass().getSimpleName(), this.mapNameInfo);
	}

}
