        private static final DecimalFormat FILE_SIZE_FORMAT = new DecimalFormat("###,###,###.##");
        public enum Unit {
                BYTE, KB, MB, GB, TB
        }

        public static String convertFileSize(double size, Unit unit) {
                return convertFileSize(FILE_SIZE_FORMAT.format(size) + unit, size, unit).replace("BYTE", "byte");
        }

        private static String convertFileSize(String ret, double size, Unit unit) {
                if (size < 1024 || unit.ordinal() + 1 > Unit.values().length - 1){
                        return ret;
                } else {
                        final double resize = size / 1024;
                        final Unit nextUnit = Unit.values()[unit.ordinal() + 1];
                        final String reunit = nextUnit.toString();
                        return convertFileSize(FILE_SIZE_FORMAT.format(resize) + reunit, resize, nextUnit) ;
                }
        }
