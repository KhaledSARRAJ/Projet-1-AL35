export interface Signalement {
  id: number,
  date_de_signalement: string,
  type: string,
  identifiant: number,
  userid: number,
  motifSignalement: MotifSignalement
}

export interface MotifSignalement {
  id: number,
  libelle_motif_signalement: string
}
