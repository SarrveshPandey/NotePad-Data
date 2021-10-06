package com.mobileapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AttachmentOnly {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("results")
    @Expose
    private Integer results;
    @SerializedName("result")
    @Expose
    private Result result;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {

        @SerializedName("attachments")
        @Expose
        private Attachments attachments;

        public Attachments getAttachments() {
            return attachments;
        }

        public void setAttachments(Attachments attachments) {
            this.attachments = attachments;
        }
    }

    public class Attachments {

        @SerializedName("result")
        @Expose
        private List<Result__1> result = null;

        public List<Result__1> getResult() {
            return result;
        }

        public void setResult(List<Result__1> result) {
            this.result = result;
        }

        public class Result__1 {

            @SerializedName("ticketid")
            @Expose
            private String ticketid;
            @SerializedName("linktypeid")
            @Expose
            private String linktypeid;
            @SerializedName("attachmentid")
            @Expose
            private String attachmentid;
            @SerializedName("filename")
            @Expose
            private String filename;
            @SerializedName("filetype")
            @Expose
            private String filetype;
            @SerializedName("base64content")
            @Expose
            private String base64content;

            public String getTicketid() {
                return ticketid;
            }

            public void setTicketid(String ticketid) {
                this.ticketid = ticketid;
            }

            public String getLinktypeid() {
                return linktypeid;
            }

            public void setLinktypeid(String linktypeid) {
                this.linktypeid = linktypeid;
            }

            public String getAttachmentid() {
                return attachmentid;
            }

            public void setAttachmentid(String attachmentid) {
                this.attachmentid = attachmentid;
            }

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getFiletype() {
                return filetype;
            }

            public void setFiletype(String filetype) {
                this.filetype = filetype;
            }

            public String getBase64content() {
                return base64content;
            }

            public void setBase64content(String base64content) {
                this.base64content = base64content;
            }

        }
    }
}
