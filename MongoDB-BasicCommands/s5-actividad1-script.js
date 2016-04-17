// mongo localhost:27017/test actividad-script.js

db = db.getSiblingDB('ITESO');
db.createCollection("Alumnos");
db.createCollection("Materias");
db.createCollection("Carreras");
db.Materias.insert({
    "Code":  "123",
    "Name":  "Materia 1",
    "Program":  "MSC"
});
db.Materias.insert({
    "Code":  "456",
    "Name":  "Materia 2",
    "Program":  "MSC"
});
db.Carreras.insert({
    "Code":  "12",
    "Name":  "M. en Sistemas Computacionales",
    "Department":  "DESI"
});
db.Carreras.insert({
    "Code":  "34",
    "Name":  "M. en Inf. Aplicada",
    "Department":  "DESI"
});
print(db.getCollectionNames());
var cursor = db.Materias.find();
while(cursor.hasNext()) {
    printjsononeline(cursor.next());
}
var cursor = db.Carreras.find();
while(cursor.hasNext()) {
    printjsononeline(cursor.next());
}
db.Materias.remove(db.Materias.findOne());
db.Materias.drop();
db.Carreras.drop();

db.Alumnos.insert({
    "Estatura":  2.04,
    "Saludable":  true,
    "Sexo":  "M",
    "Nombre":  "Anette",
    "Edad":  33,
    "Hijos":  1,
    "Apellido":  "Burfield"
});
db.Alumnos.insert({
    "Edad":  35,
    "Nombre":  "Della",
    "Sexo":  "M",
    "Saludable":  false,
    "Apellido":  "Kocsis"
});
db.Alumnos.insert({
    "Edad":  27,
    "Nombre":  "Marquis",
    "Hijos":  2,
    "Sexo":  "H",
    "Apellido":  "Pipkins"
});
db.Alumnos.insert({
    "Estatura":  1.6,
    "Saludable":  true,
    "Sexo":  "M",
    "Nombre":  "Darcey",
    "Edad":  20,
    "Hijos":  3,
    "Apellido":  "Glanz"
});
db.Alumnos.insert({
    "Edad":  30,
    "Nombre":  "Lashunda",
    "Estatura":  1.76,
    "Sexo":  "M",
    "Saludable":  false,
    "Apellido":  "Tabon",
    "Peso":  54.98
});
db.Alumnos.insert({
    "Edad":  35,
    "Nombre":  "Kaitlin",
    "Estatura":  2.06,
    "Sexo":  "M",
    "Apellido":  "Manry",
    "Peso":  94.28
});
db.Alumnos.insert({
    "Edad":  31,
    "Nombre":  "Hoyt",
    "Sexo":  "H",
    "Saludable":  true,
    "Apellido":  "Bremer",
    "Peso":  53.69
});
db.Alumnos.insert({
    "Edad":  36,
    "Nombre":  "Guillermina",
    "Hijos":  4,
    "Sexo":  "M",
    "Saludable":  false,
    "Apellido":  "Strandberg"
});
db.Alumnos.insert({
    "Edad":  26,
    "Nombre":  "Chanel",
    "Hijos":  5,
    "Sexo":  "M",
    "Apellido":  "Leaver"
});
db.Alumnos.insert({
    "Edad":  21,
    "Nombre":  "Romana",
    "Hijos":  6,
    "Sexo":  "M",
    "Saludable":  true,
    "Apellido":  "Marrinan",
    "Peso":  63.99
});
var cursor = db.Alumnos.find();
while(cursor.hasNext()) {
    printjsononeline(cursor.next());
}

// EOF
