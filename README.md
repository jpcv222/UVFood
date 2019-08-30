# UVFood
Desktop software to Servicio de Restaurante Universitario from Universidad del Valle. 
# Prerequisites
- You will need NetBeans IDE 8.1 or highest.
- You will need PostgreSQL 10 or highest.
# Installing
## Package project
- Clone or download this repository. 
- If necessary, you will need to install libraries located in UVFood/src/libraries.
## Database settings
- Create empty database in your local server: Preferred name 'uvfood'.
- Import current database file located in UVFood/src/files/bd_files/uvfood_current.sql.
```
   Suggested comand: psql -U youruser -W -h localhost uvfood < uvfood_current.sql
```
- Configure your local database connection in  file UVFood/src/classes/ConexionBD.java. 
```
    private static final String url = "jdbc:postgresql://localhost:5432/uvfood";
    private static final String user = "postgres";
    private static final String password = "pgsql";
```
## Run project
- Run file UVFood/src/UVFood.java to execute project.
# Authors

* **Juan Pablo Castro** - *Engineer database, Developer* - [jpcv222](https://github.com/jpcv222)
* **Febe Gaviria**      - *Scrum Master, Developer* - [FebeGavi](https://github.com/FebeGavi)
* **Jeffrey Rios**      - *Product Owner, Developer* - [jeffrey2423](https://github.com/jeffrey2423)
* **Hanier Peña**       - *QA, Developer* - [HanierPena](https://github.com/HanierPena)

See also the list of [contributors](https://github.com/jpcv222/UVFood/contributors) who participated in this project.

# License

This project is licensed under the GNU License - see the [GNU License](https://www.gnu.org/licenses/licenses.es.html) file for details
