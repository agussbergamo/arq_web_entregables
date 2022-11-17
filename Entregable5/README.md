
# Servicio POST para dar de alta un estudiante 
** (el estudiante del JSON ya existe en la base de datos, para probarlo cambiarle los datos del data-raw, si no se recibe un error como: **
** ""status":400,"error":"Bad Request","path":"/estudiantes/"") **

    curl --location --request POST 'https://arq-web-entregable5.herokuapp.com/estudiantes/' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "dni": 13647956,
        "nombre": "Marta",
        "apellido": "Sanchez",
        "edad": 24,
        "genero": "Femenino",
        "ciudad": "Jujuy",
        "num_libreta": 4976
    }'

# Servicio GET para obtener el listado de carreras

    curl --location --request GET 'https://arq-web-entregable5.herokuapp.com/carreras/'


