/**
 * {
    "adresseAPI": {
      "idAdresse": "4d2e6a55-fe53-4053-8041-7fb6e85f21d2",
      "ligneAdresse1": "20 Allée du Carroussel",
      "ligneAdresse2": "",
      "ligneAdresse3": "",
      "ville": "Villenave d'Ornon"
    },
    "idPersonne": "5084c013-214d-45e1-bf9f-1d012f4e5a29",
    "nom": "FABRE",
    "prenom": "Julien"
  },
 */

 export interface Adresse {
    idAdresse: string;
    ligneAdresse1: string;
    ligneAdresse2: string;
    ligneAdresse3: string;
    ville: string;
 }

 export interface Personne {
     idPersonne : string;
     nom: string;
     prenom: string;
     adresseAPI:  Adresse;
 }