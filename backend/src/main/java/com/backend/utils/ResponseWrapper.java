package com.backend.utils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ResponseWrapper extends HttpServletResponseWrapper {
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private PrintWriter writer;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {
            }

            @Override
            public void write(byte[] b, int off, int len) {
                bos.write(b, off, len);
            }

            @Override
            public void write(int b) throws IOException {
                bos.write(b);
            }
        };
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        this.writer = new PrintWriter(new OutputStreamWriter(bos, this.getCharacterEncoding()));
        return this.writer;
    }

    public String getBody() throws IOException {
        if (writer != null) {
            writer.close();
        }
        return this.bos.toString(this.getCharacterEncoding());
    }
}