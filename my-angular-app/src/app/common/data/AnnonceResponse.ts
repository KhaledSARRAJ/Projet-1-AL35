import { SupportResponse } from "./SupportResponse";
import { ThemeResponse } from "./ThemeResponse";

export class AnnonceResponse {
    constructor(
        public id: number = 0,
        public titre: string = "",
        public texte: string = "",
        public prix: number = 0,
        public dateParution: string = "", // ????????? format date
        public prenomDupublisher: string = "",
        public listeDesSupports: SupportResponse[] = [],
        public listeDesThemes: ThemeResponse[] = [],
    ) {
    }
}