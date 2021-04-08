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

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}

			if (!(obj instanceof Name)) {
				return false;
			}

			Name n = (Name) obj;
			if (this.name == null && n.name == null) {
				return true;
			}

			if (n.name != null && this.name != null && this.name.equals(n.name)) {
				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			if (this.name == null) {
				return 0;
			} else {
				return this.name.hashCode();
			}
		}

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

		@Override
		public boolean equals(Object obj) {
			boolean r = super.equals(obj);

			if (r == false) {
				return false;
			}

			FullName n = (FullName) obj;

			if (this.lastName == null && n.lastName == null) {
				return true;
			}

			if (this.lastName == null || n.lastName == null) {
				return false;
			}

			if (this.lastName.equals(n.lastName)) {
				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			if (this.lastName == null) {
				return super.hashCode();
			} else {
				return super.hashCode() * 3 + this.lastName.hashCode() * 7;
			}

		}

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
