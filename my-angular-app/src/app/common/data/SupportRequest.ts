export class SupportRequest {
    constructor(
        public id: number | null = null,
        public typeSupport: string = "",
        public chemin: string = "",
        public image: string = "", 
        public annonceId: number | null = null,
    ) {
    }
}