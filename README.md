# Projet-Bar
Projet ASI2

1 - Faire attention aux différents chemin de dépendeces
2 - Ajout le datasource barDS au standalone.xml :
	=> Aller dans le dossier du standalone (.\wildfly-20.0.1.Final\standalone\configuration\)
	=> Ajouter le DS avec les bons username et password correspondant à ton utilisateur de connexion a ton phpMyAdmin
		<datasource jndi-name="java:jboss/datasources/BarDS" pool-name="BarDS" enabled="true" use-java-context="true">
            <connection-url>jdbc:mysql://localhost:3306/bar_bdd?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC</connection-url>
            <driver>mysql</driver>
            <security>
				<user-name>root</user-name>
			<security>
        </datasource>