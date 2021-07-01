export class AnnonceRequest {
    constructor(
        public id: number | null = null,
        public titre: string = "",
        public texte: string = "",
        public prix: number = 0,
        public userId: number = 0
    ) {
    }
}