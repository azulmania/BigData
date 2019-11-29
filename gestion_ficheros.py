def obtener_items_fichero(origen='tareas.dat', separador = ","):
	try:
		items = []
		with open(origen) as f_items:
			for item in f_items:
				items.extend(item.rstrip().split(separador))
		return items
	except:
		print("Problemas en el paraiso")
	finally:
		print("Fin")
