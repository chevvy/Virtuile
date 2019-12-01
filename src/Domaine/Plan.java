package Domaine;

import MVC.Etat;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Plan {

    private ArrayList<Surface> listeSurfaces = new ArrayList<>();
    public Surface surfaceSelectionnee;
    private Surface surfaceOriginale;
    private Surface ancre;
    private Point premierPoint;
    private Point pointPrecedent;
    private ArrayList<Point> surfaceLibre;
    private boolean isGrilleMagnetiqueActive = false;
    private int grid_size = 50;
    private int grab = 0;
    private int base = 0;
    private ArrayList<String> listeCouleurs = new ArrayList<>( Arrays.asList("Rouge", "Noir", "Gris", "Jaune", "Bleu"));
    private ArrayList<String> listeTypeMateriau = new ArrayList<>( Arrays.asList("Béton", "Terre cuite", "Ardoise", "Céramique",
            "Bois", "Aucun Revêtement"));
    private ArrayList<String> listeMotifs= new ArrayList<>( Arrays.asList("Installation droite", "Installation " +
            "imitation parquet", "Installation en décallé", "Installation en chevron", "Installation en L"));


    public Plan(){
    }

    public Etat selectionner(Point position){
        if(surfaceSelectionnee != null){
            if(trouverPointAncrage(position) == Etat.ETIRER_SURFACE){
                return Etat.ETIRER_SURFACE;
            }
        }
        if(surfaceSelectionnee != null && surfaceSelectionnee.polygone.contains(position)){
            pointPrecedent = position;
            return Etat.DEPLACER_SURFACE;
        }
        for(Surface surface : listeSurfaces){
            if(surface.polygone.contains(position)){
                surfaceOriginale = surfaceSelectionnee = surface;
                return Etat.LECTURE;
            }
        }
        surfaceSelectionnee = null;
        return Etat.LECTURE;
    }

    public void supprimerSurface(){
        listeSurfaces.remove(surfaceSelectionnee);
        surfaceSelectionnee = null;
    }

    public Etat initialiserSurface(Point position, ArrayList<Point> patron, boolean trou){
        premierPoint = convertMouseCoordWithMagnetique(position);
        grab = 0;
        ArrayList<Point> patronAjuste = patron.stream().map(point -> new Point(point.x + premierPoint.x, point.y + premierPoint.y))
                .collect(Collectors.toCollection(ArrayList::new));
        surfaceSelectionnee = new Surface(patronAjuste, trou);
        surfaceOriginale = surfaceSelectionnee.clone();
        listeSurfaces.add(surfaceSelectionnee);
        return Etat.ETIRER_SURFACE;
    }

    public void initialiserSurfaceLibre(){
        surfaceLibre = new ArrayList<>();
    }

    private void terminerSurfaceLibre(boolean trou){
        surfaceSelectionnee = new Surface(surfaceLibre, trou);
        listeSurfaces.add(surfaceSelectionnee);
    }

    public Etat ajouterPointSurfaceLibre(Point point, boolean trou){
        point = convertMouseCoordWithMagnetique(point);
        if(surfaceLibreIsFirst(point)) {
            terminerSurfaceLibre(trou);
            return Etat.LECTURE;
        }
        else{
            surfaceLibre.add(point);
            return Etat.CREER_FORME_LIBRE;
        }
    }

    private boolean surfaceLibreIsFirst(Point point){
        if (surfaceLibre.size() < 3) { return false; }
        Point first = surfaceLibre.get(0);
        return isCloseToPoint(point, first);
    }

    private Etat trouverPointAncrage(Point position){
        Rectangle rect = surfaceSelectionnee.polygone.getBounds();
        if(isCloseToPoint(position, new Point(rect.x, rect.y))){
            // Top Right
            premierPoint = new Point(rect.x + rect.width, rect.y + rect.height);
            grab = 0;
            surfaceOriginale = surfaceSelectionnee.clone();
            surfaceOriginale.flipHorizontal();
            surfaceOriginale.flipVertical();
            return  Etat.ETIRER_SURFACE;
        }
        else if(isCloseToPoint(position, new Point(rect.x + rect.width, rect.y))){
            // Top Left
            premierPoint = new Point(rect.x, rect.y + rect.height);
            grab = 0;
            surfaceOriginale = surfaceSelectionnee.clone();
            surfaceOriginale.flipVertical();
            return  Etat.ETIRER_SURFACE;
        }
        else if(isCloseToPoint(position, new Point(rect.x + rect.width, rect.y + rect.height))){
            // Bottom Left
            premierPoint = new Point(rect.x, rect.y);
            grab = 0;
            surfaceOriginale = surfaceSelectionnee.clone();
            return  Etat.ETIRER_SURFACE;
        }
        else if(isCloseToPoint(position, new Point(rect.x, rect.y + rect.height))){
            // Bottom Right
            premierPoint = new Point(rect.x + rect.width, rect.y);
            grab = 0;
            surfaceOriginale = surfaceSelectionnee.clone();
            surfaceOriginale.flipHorizontal();
            return  Etat.ETIRER_SURFACE;
        }
        else if(position.x < rect.x+5 && position.x > rect.x-5 && position.y > rect.y && position.y < rect.y+rect.height){
            // Left
            premierPoint = new Point(rect.x + rect.width, rect.y);
            grab = 1;
            base = rect.y+rect.height;
            surfaceOriginale = surfaceSelectionnee.clone();
            surfaceOriginale.flipHorizontal();
            return  Etat.ETIRER_SURFACE;
        }
        else if(position.x < rect.x+rect.width+5 && position.x > rect.x+rect.width-5 && position.y > rect.y && position.y < rect.y+rect.height){
            // Right
            premierPoint = new Point(rect.x, rect.y);
            grab = 1;
            base = rect.y+rect.height;
            surfaceOriginale = surfaceSelectionnee.clone();
            return  Etat.ETIRER_SURFACE;
        }
        else if(position.y < rect.y+5 && position.y > rect.y-5 && position.x > rect.x && position.x < rect.x+rect.width){
            // Top
            premierPoint = new Point(rect.x, rect.y + rect.height);
            grab = 2;
            base = rect.x+rect.width;
            surfaceOriginale = surfaceSelectionnee.clone();
            surfaceOriginale.flipVertical();
            return  Etat.ETIRER_SURFACE;
        }
        else if(position.y < rect.y+rect.height+5 && position.y > rect.y+rect.height-5 && position.x > rect.x && position.x < rect.x+rect.width){
            // Bottom
            premierPoint = new Point(rect.x, rect.y);
            grab = 2;
            base = rect.x+rect.width;
            surfaceOriginale = surfaceSelectionnee.clone();
            return  Etat.ETIRER_SURFACE;
        }
        return Etat.LECTURE;
    }

    private boolean isCloseToPoint(Point test, Point location){
        if (test.x < location.x-5 || test.x > location.x+5) {return false;}
        if (test.y < location.y-5 || test.y > location.y+5) {return false;}
        return true;
    }

    public ArrayList<Point> getSurfaceLibre(){
        return  surfaceLibre;
    }

    public void deplacerSurface(Point position){
        int deplacement_x = position.x - pointPrecedent.x;
        int deplacement_y = position.y - pointPrecedent.y;
        surfaceSelectionnee.deplacerSurface(deplacement_x, deplacement_y);
        pointPrecedent = position;
        if(isGrilleMagnetiqueActive){
            int x = surfaceSelectionnee.polygone.getBounds().x;
            int y = surfaceSelectionnee.polygone.getBounds().y;
            Point top = new Point(x, y);
            Point newTop = convertMouseCoordWithMagnetique(top);
            deplacement_x = newTop.x - top.x;
            deplacement_y = newTop.y - top.y;
            surfaceSelectionnee.deplacerSurface(deplacement_x, deplacement_y);
            pointPrecedent.x += deplacement_x;
            pointPrecedent.y += deplacement_y;
        }
    }

    public void setDimensionsSurface(int hauteur, int largeur){ //À déplacer dans surface
        surfaceSelectionnee.setDimensions(hauteur, largeur);

    }

    public void etirerSurface(Point position){
        if (grab == 1){position.y = base;}
        if (grab == 2){position.x = base;}
        position = convertMouseCoordWithMagnetique(position);
        int x  = position.x - premierPoint.x;
        int y = position.y - premierPoint.y;
        ArrayList<Point> points = surfaceOriginale.getListePoints();
        Rectangle limites = surfaceOriginale.polygone.getBounds();
        if(x != 0 && y!= 0){
            points = points.stream().map(point ->{
                int nouveau_x = (x * Math.abs(point.x - limites.x) / limites.width) + premierPoint.x;
                int nouveau_y = (y * Math.abs(point.y - limites.y) / limites.height) + premierPoint.y;
                return new Point(nouveau_x, nouveau_y);
            }).collect(Collectors.toCollection(ArrayList::new));
            surfaceSelectionnee.changerPoints(points);
            for(int trou = 0; trou < surfaceOriginale.trous.size(); trou++){
                points = surfaceOriginale.trous.get(trou).getListePoints().stream().map(point ->{
                    int nouveau_x = (x * Math.abs(point.x - limites.x) / limites.width) + premierPoint.x;
                    int nouveau_y = (y * Math.abs(point.y - limites.y) / limites.height) + premierPoint.y;
                    return new Point(nouveau_x, nouveau_y);
                }).collect(Collectors.toCollection(ArrayList::new));
                surfaceSelectionnee.trous.get(trou).changerPoints(points);
            }
        }
        //Surface nouvelleSurface = new Surface(points, surfaceOriginale.estUnTrou, surfaceOriginale.getRevetement());
        //listeSurfaces.remove(surfaceSelectionnee);
        //listeSurfaces.add(nouvelleSurface);
        //surfaceSelectionnee = nouvelleSurface;
    }

    public Etat selectionnerAligner(Point position){
        if(surfaceSelectionnee == null || surfaceSelectionnee.polygone.contains(position)){
            return Etat.LECTURE;
        }
        for(Surface surface : listeSurfaces){
            if(surface.polygone.contains(position)){
                ancre = surface;
                premierPoint = pointPrecedent = new Point(
                        surfaceSelectionnee.polygone.getBounds().x,
                        surfaceSelectionnee.polygone.getBounds().y);
                return Etat.OUVRIR_FENETRE_ALIGNER;
            }
        }
        return Etat.LECTURE;
    }

    public void fusionner(Point p){
        if(surfaceSelectionnee == null){
            return;
        }
        for(Surface surface : listeSurfaces){
            if(surface.polygone.contains(p) && surface!=surfaceSelectionnee){
                if(surfaceSelectionnee.estUnTrou ^ surface.estUnTrou){
                    if(surface.estUnTrou){
                        if(surfaceSelectionnee.fusionnerTrou(surface)) {
                            listeSurfaces.remove(surface);
                        }
                        return;
                    }
                    if(surface.fusionnerTrou(surfaceSelectionnee)){
                        listeSurfaces.remove(surfaceSelectionnee);
                        surfaceSelectionnee = surface;
                    }
                    return;
                }
                if(surfaceSelectionnee.fusionner(surface)){
                    listeSurfaces.remove(surface);
                }
                return;
            }
        }
    }

    public void aligner(String alignement){
        Rectangle boiteAncre = ancre.polygone.getBounds();
        Rectangle boiteSelect = surfaceSelectionnee.polygone.getBounds();
        switch(alignement){
            case "gaucheExt":
                surfaceSelectionnee.deplacerSurface(boiteAncre.x - boiteSelect.width - pointPrecedent.x, 0);
                break;
            case "gaucheInt":
                surfaceSelectionnee.deplacerSurface(boiteAncre.x - pointPrecedent.x, 0);
                break;
            case "droiteExt":
                surfaceSelectionnee.deplacerSurface(boiteAncre.x + boiteAncre.width - pointPrecedent.x, 0);
                break;
            case "droiteInt":
                surfaceSelectionnee.deplacerSurface(boiteAncre.x + boiteAncre.width - boiteSelect.width - pointPrecedent.x, 0);
                break;
            case "centreHorizontal":
                surfaceSelectionnee.deplacerSurface(boiteAncre.x + (boiteAncre.width - boiteSelect.width)/2 - pointPrecedent.x, 0);
                break;
            case "basExt":
                surfaceSelectionnee.deplacerSurface(0, boiteAncre.y + boiteAncre.height - pointPrecedent.y);
                break;
            case "basInt":
                surfaceSelectionnee.deplacerSurface(0, boiteAncre.y + boiteAncre.height - boiteSelect.height - pointPrecedent.y);
                break;
            case "hautExt":
                surfaceSelectionnee.deplacerSurface(0, boiteAncre.y - boiteSelect.height - pointPrecedent.y);
                break;
            case "hautInt":
                surfaceSelectionnee.deplacerSurface(0, boiteAncre.y - pointPrecedent.y);
                break;
            case "centreVertical":
                surfaceSelectionnee.deplacerSurface(0, boiteAncre.y + (boiteAncre.height - boiteSelect.height)/2 - pointPrecedent.y);
                break;
            case "rienHorizontal":
                surfaceSelectionnee.deplacerSurface(premierPoint.x - pointPrecedent.x, 0);
                break;
            case "rienVertical":
                surfaceSelectionnee.deplacerSurface(0, premierPoint.y - pointPrecedent.y);
                break;
            default:
                break;
        }
        pointPrecedent = surfaceSelectionnee.polygone.getBounds().getLocation();
    }

    public void annulerAligner(){
        deplacerSurface(premierPoint);
    }

    public ArrayList<Surface> recupererSurfaces(){
        return listeSurfaces;
    }

    public void setGridSize(int size){
        grid_size = size;
    }

    public int getGridSize(){
        return grid_size;
    }

    public void setGrilleMagnetiqueActive(boolean active){
        this.isGrilleMagnetiqueActive = active;
    }

    private Point convertMouseCoordWithMagnetique(Point point){
        if (isGrilleMagnetiqueActive){
            int offX = point.x % grid_size;
            int offY = point.y % grid_size;
            Point nouveauPoint = new Point();
            nouveauPoint.x = offX < grid_size / 2 ? point.x - offX : point.x - offX + grid_size;
            nouveauPoint.y = offY < grid_size / 2 ? point.y - offY : point.y - offY + grid_size;
            return nouveauPoint;
        }
        return point;
    }

    public ArrayList<Surface> getListeSurfaces() {
        return listeSurfaces;
    }


    // Liste des couleurs de tuiles
    public void ajouterCouleur(String nom){
        if (!(listeCouleurs.contains(nom)) && !nom.equals(""))
            this.listeCouleurs.add(nom);
    }

    public ArrayList<String> getListeCouleur(){return listeCouleurs;}

    //Liste des types de matériaux
    public void ajouterTypeMateriau(String nom){
        if (!(listeTypeMateriau.contains(nom)))
        this.listeTypeMateriau.add(nom);
    }


    public ArrayList<String> getListeTypeMateriau(){return listeTypeMateriau;}

    //Liste des types de motifs
    public ArrayList<String> getListeMotifs(){return listeMotifs;}

    public Map<String, String> getInfosSurface(Surface surface) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Hauteur surface", Integer.toString(surfaceSelectionnee.polygone.getBounds().height));
        map.put("Longueur surface", Integer.toString(surfaceSelectionnee.polygone.getBounds().width));
        map.put("Couleur coulis", String.valueOf(surface.getCouleurCoulis().getRGB()));
        map.put("Épaisseur coulis", Integer.toString(surface.getTailleDuCoulis()));
        map.put("Est un trou", Boolean.toString(surface.estUnTrou));
        return map;
    }

}



