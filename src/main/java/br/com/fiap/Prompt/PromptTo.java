package br.com.fiap.Prompt;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

//import br.com.fiap.Entity.Usuario;

public abstract class PromptTo<T> {
	private T clazz;
	private Scanner scanner;

	public PromptTo(T clazz) {
		this.clazz = clazz;
		this.scanner = new Scanner(System.in);
	}
	

	public T prompt() throws IllegalAccessException {
		Scanner input = this.scanner;

		Field[] fields = this.clazz.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().contains("id")) {
				
					continue; // Skip password field
							
			
			} else if (field.getType() == List.class) {
				continue;// Skip lists fields while creating
			}

			String value;

			try {
				Method setMethod = this.clazz.getClass()
						.getDeclaredMethod("set" + StringUtils.capitalize(field.getName().toString()), field.getType());

				field.setAccessible(true);

				if (field.getType().isEnum()) {

					Object[] enumValues = field.getType().getEnumConstants();

					System.out.println("Informe o(a) "
							+ StringUtils.capitalize(
									StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(field.getName()), " "))
							+ " escolha uma das opções : ");
					for (int i = 0; i < enumValues.length; i++) {
						System.out.println((i + 1) + ". " + enumValues[i]);
					}
					int choice = Integer.parseInt(scanner.nextLine());

					setMethod.invoke(this.clazz, enumValues[choice - 1]);

				} else {
					System.out.println("Informe o(a) "
							+ StringUtils.capitalize(
									StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(field.getName()), " "))
							+ ":");
					value = input.nextLine();
					setMethod.invoke(this.clazz, value);
				}

			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return this.clazz;
	}

	@Override
	public String toString() {
		Field[] fields = this.clazz.getClass().getDeclaredFields();
		String returned = "";
		for (Field field : fields) {
			if (field.getName().contains("id")) {
				continue; // Skip password field
			} else if (field.getType() == List.class) {
				continue;// Skip lists fields while creating
			}
			field.setAccessible(true);

			try {
				Object value = field.get(this.clazz);
				returned += " | " + field.getName() + ": " + value.toString() + " | ";
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returned;

	}
}
