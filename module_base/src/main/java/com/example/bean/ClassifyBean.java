package com.example.bean;

import java.io.Serializable;
import java.util.List;

public class ClassifyBean implements Serializable {
    private String id;
    private String parentId;
    private String name;
    private String level;
    private String productCount;
    private String productUnit;
    private String navStatus;
    private String showStatus;
    private String sort;
    private String icon;
    private String keywords;
    private String description;
    private String path;
    private String productAttributeIdList;
    private boolean isSelect;
    private List<ClassifySecond> children;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getProductCount() {
        return productCount;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public String getNavStatus() {
        return navStatus;
    }

    public String getShowStatus() {
        return showStatus;
    }

    public String getSort() {
        return sort;
    }

    public String getIcon() {
        return icon;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getDescription() {
        return description;
    }

    public String getPath() {
        return path;
    }

    public String getProductAttributeIdList() {
        return productAttributeIdList;
    }

    public List<ClassifySecond> getChildren() {
        return children;
    }

    public static class ClassifySecond {
        private String id;
        private String parentId;
        private String name;
        private String level;
        private String productCount;
        private String productUnit;
        private String navStatus;
        private String showStatus;
        private String sort;
        private String icon;
        private String keywords;
        private String description;
        private String path;
        private String productAttributeIdList;
        private List<ClassifyThird> children;

        public String getId() {
            return id;
        }

        public String getParentId() {
            return parentId;
        }

        public String getName() {
            return name;
        }

        public String getLevel() {
            return level;
        }

        public String getProductCount() {
            return productCount;
        }

        public String getProductUnit() {
            return productUnit;
        }

        public String getNavStatus() {
            return navStatus;
        }

        public String getShowStatus() {
            return showStatus;
        }

        public String getSort() {
            return sort;
        }

        public String getIcon() {
            return icon;
        }

        public String getKeywords() {
            return keywords;
        }

        public String getDescription() {
            return description;
        }

        public String getPath() {
            return path;
        }

        public String getProductAttributeIdList() {
            return productAttributeIdList;
        }

        public List<ClassifyThird> getChildren() {
            return children;
        }

        public static class ClassifyThird {
            private String id;
            private String parentId;
            private String name;
            private String level;
            private String productCount;
            private String productUnit;
            private String navStatus;
            private String showStatus;
            private String sort;
            private String icon;
            private String keywords;
            private String description;
            private String path;
            private String productAttributeIdList;
            private List children;

            public String getId() {
                return id;
            }

            public String getParentId() {
                return parentId;
            }

            public String getName() {
                return name;
            }

            public String getLevel() {
                return level;
            }

            public String getProductCount() {
                return productCount;
            }

            public String getProductUnit() {
                return productUnit;
            }

            public String getNavStatus() {
                return navStatus;
            }

            public String getShowStatus() {
                return showStatus;
            }

            public String getSort() {
                return sort;
            }

            public String getIcon() {
                return icon;
            }

            public String getKeywords() {
                return keywords;
            }

            public String getDescription() {
                return description;
            }

            public String getPath() {
                return path;
            }

            public String getProductAttributeIdList() {
                return productAttributeIdList;
            }

            public List getChildren() {
                return children;
            }

            @Override
            public String toString() {
                return "ClassifyThird{" +
                        "id='" + id + '\'' +
                        ", parentId='" + parentId + '\'' +
                        ", name='" + name + '\'' +
                        ", level='" + level + '\'' +
                        ", productCount='" + productCount + '\'' +
                        ", productUnit='" + productUnit + '\'' +
                        ", navStatus='" + navStatus + '\'' +
                        ", showStatus='" + showStatus + '\'' +
                        ", sort='" + sort + '\'' +
                        ", icon='" + icon + '\'' +
                        ", keywords='" + keywords + '\'' +
                        ", description='" + description + '\'' +
                        ", path='" + path + '\'' +
                        ", productAttributeIdList='" + productAttributeIdList + '\'' +
                        ", children=" + children +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ClassifySecond{" +
                    "id='" + id + '\'' +
                    ", parentId='" + parentId + '\'' +
                    ", name='" + name + '\'' +
                    ", level='" + level + '\'' +
                    ", productCount='" + productCount + '\'' +
                    ", productUnit='" + productUnit + '\'' +
                    ", navStatus='" + navStatus + '\'' +
                    ", showStatus='" + showStatus + '\'' +
                    ", sort='" + sort + '\'' +
                    ", icon='" + icon + '\'' +
                    ", keywords='" + keywords + '\'' +
                    ", description='" + description + '\'' +
                    ", path='" + path + '\'' +
                    ", productAttributeIdList='" + productAttributeIdList + '\'' +
                    ", children=" + children +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ClassifyBean{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", productCount='" + productCount + '\'' +
                ", productUnit='" + productUnit + '\'' +
                ", navStatus='" + navStatus + '\'' +
                ", showStatus='" + showStatus + '\'' +
                ", sort='" + sort + '\'' +
                ", icon='" + icon + '\'' +
                ", keywords='" + keywords + '\'' +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", productAttributeIdList='" + productAttributeIdList + '\'' +
                ", children=" + children +
                '}';
    }
}
