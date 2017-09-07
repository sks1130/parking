/**
 * 
 */
package models;

/**
 * @author sachin.srivastava
 *
 */
public class Cars {
	
		String registrationNumber;
		String color;
		
		public Cars(String registrationNumber,String color){
			this.registrationNumber = registrationNumber;
			this.color = color;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((color == null) ? 0 : color.hashCode());
			result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cars other = (Cars) obj;
			if (color == null) {
				if (other.color != null)
					return false;
			} else if (!color.equals(other.color))
				return false;
			if (registrationNumber == null) {
				if (other.registrationNumber != null)
					return false;
			} else if (!registrationNumber.equals(other.registrationNumber))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Cars [registrationNumber=" + registrationNumber + ", color=" + color + "]";
		}

		public String getRegistrationNumber() {
			return registrationNumber;
		}

		public void setRegistrationNumber(String registrationNumber) {
			this.registrationNumber = registrationNumber;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}
}
