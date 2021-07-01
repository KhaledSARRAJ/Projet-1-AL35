export class ThemeRequest {
    constructor(
        public id: number,
        public titre: string,
        public description: String,
        public annoncesId: number[], 
    ) {
    }
}